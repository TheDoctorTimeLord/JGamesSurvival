package com.jgames.survival.model.game.logic.battle.commands.move;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.movement.CanMoved;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;
import com.jgames.survival.model.game.logic.battle.utils.LocationUtils;

/**
 * Описывает фабрику, создающую команды перемещения
 */
@BattleBeanPrototype
public class MoveCommandFactory implements BattleCommandFactory<SelectionFromSetParameters<Point>, MoveCommand> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return model instanceof CanMoved;
    }

    /**
     * Команда доступна для выбора персонажу, если:
     * <ol>
     * <li>Персонаж может перемещаться (атрибут moveDistance > 0)</li>
     * <li>Есть хотя бы одна свободная клетка рядом с ним (слева, справа, спереди, слева-спереди или справа-спереди)
     * </li>
     * </ol>
     */
    @Override
    public boolean isAvailableCommand(BattleModel battleModel, BattleContext battleContext) {
        return checkCanMove(battleModel) && hasAvailablePosition((CanMoved)battleModel, battleContext.getBattleState());
    }

    @Override
    public MoveCommand createBattleCommand(BattleModel battleModel, BattleContext battleContext) {
        return new MoveCommand(getAvailablePosition((CanMoved)battleModel, battleContext.getBattleState()));
    }

    /**
     * Проверяет, способен ли персонаж перемещаться
     * @param battleModel персонаж
     */
    public static boolean checkCanMove(BattleModel battleModel) {
        IntAttribute moveDistance =  battleModel.getAttributes().getAttributeByPath(Attributes.MOVE_DISTANCE);
        return moveDistance != null && moveDistance.getValue() > 0;
    }

    private static Set<Point> getAvailablePosition(CanMoved canMoved, BattleState battleState) {
        Point modelPosition = canMoved.getPosition();
        Direction modelDirection = canMoved.getDirection();
        List<Point> pointNeighbour = LocationUtils.getNeighbours(modelPosition, battleState,
                modelDirection.getOffset(),
                modelDirection.rotateLeft().getOffset(),
                modelDirection.rotateRight().getOffset()
        );

        return pointNeighbour.stream()
                .filter(point -> battleState.getOnPosition(point).isEmpty())
                .collect(Collectors.toSet());
    }

    public static boolean hasAvailablePosition(CanMoved canMoved, BattleState battleState) {
        return !getAvailablePosition(canMoved, battleState).isEmpty();
    }

    @Nullable
    private static Point checkPosition(Point position, BattleState state) {
        return state.inBattlefieldBound(position) && state.getOnPosition(position).isEmpty() ? position : null;
    }
}
