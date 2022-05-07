package com.jgames.survival.model.game.logic.battle.commands.move;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants;
import com.jgames.survival.model.game.logic.battle.models.CanMoveExtension;

/**
 * Описывает фабрику, создающую команды перемещения
 */
@BattleBeanPrototype
public class MoveCommandFactory implements BattleCommandFactory<MoveParameters, MoveCommand> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return model instanceof CanMoveExtension;
    }

    /**
     * Команда доступна для выбора персонажу, если:
     * <ol>
     * <li>Персонаж может перемещаться (есть атрибут canMove в features)</li>
     * <li>Есть хотя бы одна свободная клетка рядом с ним (слева, справа, спереди, слева-спереди или справа-спереди)
     * </li>
     * </ol>
     */
    @Override
    public boolean isAvailableCommand(BattleModel battleModel, BattleContext battleContext) {
        CanMoveExtension canMove = (CanMoveExtension)battleModel;
        return checkCanMove(battleModel) && canMove.hasAvailablePosition(battleContext.getBattleState());
    }

    @Override
    public MoveCommand createBattleCommand(BattleModel battleModel, BattleContext battleContext) {
        return new MoveCommand(((CanMoveExtension)battleModel).getAvailablePosition(battleContext.getBattleState()));
    }

    /**
     * Проверяет, способен ли персонаж перемещаться (есть атрибут canMove в features)
     * @param battleModel персонаж
     */
    public static boolean checkCanMove(BattleModel battleModel) {
        AttributeMarker canMove =  battleModel.getAttributes()
                .getAsContainer(AttributesConstants.Features.FEATURES)
                .get(AttributesConstants.Features.CAN_MOVE);
        return canMove != null;
    }
}
