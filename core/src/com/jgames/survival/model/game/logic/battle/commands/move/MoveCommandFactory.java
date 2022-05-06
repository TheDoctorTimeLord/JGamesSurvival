package com.jgames.survival.model.game.logic.battle.commands.move;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants;
import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.movement.CanMoved;

import java.util.HashSet;
import java.util.Set;

/**
 * Описывает фабрику перемещения, создающую объект команды.
 * В обязанности фабрики также входит определить может ли конкретный динамический объект
 * выполнять данную команду в бою и доступна ли эта команда объекту в текущем ходу.
 */
@BattleBeanPrototype
public class MoveCommandFactory implements BattleCommandFactory<MoveParameters, MoveCommand> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return CanMoved.castToCanMoved(model) != null;
    }

    /**
     * Команда доступна для выбора персонажу, если:
     * Персонаж может перемещаться (есть атрибут canMove в features)
     * Есть хотя бы одна свободная клетка рядом с ним (слева, справа, спереди, слева-спереди или справа-спереди)
     */
    @Override
    public boolean isAvailableCommand(BattleModel battleModel, BattleContext battleContext) {
        return checkCanMove(battleModel) && existEmptyPosition(battleModel, battleContext);
    }

    /**
     * Проверяет, способен ли персонаж перемещаться (есть атрибут canMove в features)
     * @param battleModel персонаж
     */
    public static boolean checkCanMove(BattleModel battleModel) {
        AttributeMarker canMove =  battleModel.getAttributes().
                getAsContainer(AttributesConstants.Features.FEATURES).get(AttributesConstants.Features.CAN_MOVE);
        return canMove != null;
    }

    /**
     * Проверяет, есть ли свободная клетка для перемещения рядом с персонажем (слева, справа, спереди, слева-спереди или справа-спереди)
     */
    private boolean existEmptyPosition(BattleModel battleModel, BattleContext battleContext) {
        BattleState battleState = battleContext.getBattleState();
        CanMoved canMoved = (CanMoved)battleModel;
        Point position = canMoved.getPosition();
        Direction direction = canMoved.getDirection();

        return checkAvailablePosition(position, direction.rotateLeft(), battleState)
                || checkAvailablePosition(position, direction.rotateRight(), battleState)
                || checkAvailablePosition(position, direction, battleState)
                || checkAvailablePosition(direction.getOffset(), direction.rotateLeft(), battleState)
                || checkAvailablePosition(direction.getOffset(), direction.rotateRight(), battleState);
    }

    /**
     * Проверяет, доступна ли клетка для передвижения на неё
     * @param currentPosition текущая позиция персонажа
     * @param direction направления взгляда персонажа
     * @param battleState хранит всю необходимую информацию о текущем состоянии боя
     * @return true - доступна для передвижения на неё, false - не доступна
     */
    private static boolean checkAvailablePosition(Point currentPosition, Direction direction, BattleState battleState) {
        return isAvailablePoint(currentPosition.add(direction.getOffset()), battleState);
    }

    /**
     * Создаёт команду перемещения
     */
    @Override
    public MoveCommand createBattleCommand(BattleModel battleModel, BattleContext battleContext) {
        return new MoveCommand(getAvailablePositions(battleModel, battleContext));
    }

    /**
     * Возвращает позиции рядом с персонажем, доступные для перемещения на них
     */
    private static Set<Point> getAvailablePositions(BattleModel battleModel, BattleContext battleContext) {
        Set<Point> availablePositions = new HashSet<>();
        BattleState battleState = battleContext.getBattleState();
        CanMoved canMoved = (CanMoved)battleModel;
        Point position = canMoved.getPosition();
        Direction direction = canMoved.getDirection();

        Direction currentDirection = direction.rotateLeft();
        for (int i = 0; i < 3; i++) {
            Point point = position.add(currentDirection.getOffset());
            if (isAvailablePoint(point, battleState)) {
                availablePositions.add(point);
            }
            currentDirection = currentDirection.rotateRight();
        }

        Point point = direction.getOffset().add(direction.rotateLeft().getOffset());
        if (isAvailablePoint(point, battleState))
            availablePositions.add(point);

        point = direction.getOffset().add(direction.rotateRight().getOffset());
        if (isAvailablePoint(point, battleState))
            availablePositions.add(point);

        return availablePositions;
    }

    /**
     * Проверяет, свободна ли клетка на поле боя
     * @param point проверяемая клетка
     * @param battleState хранит всю необходимую информацию о текущем состоянии боя
     * @return true - свободна, false - не свободна
     */
    public static boolean isAvailablePoint(Point point, BattleState battleState) {
        return battleState.inBattlefieldBound(point) && battleState.getOnPosition(point).isEmpty();
    }
}
