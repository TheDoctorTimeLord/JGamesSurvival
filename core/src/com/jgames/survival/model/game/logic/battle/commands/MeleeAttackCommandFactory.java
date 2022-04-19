package com.jgames.survival.model.game.logic.battle.commands;

import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.Fighter;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.models.BattleModel;

import javax.annotation.Nullable;

/**
 * Описывает фабрику ближнего боя, создающую объект команды.
 * В обязанности фабрики также входит определить может ли конкретный динамический объект
 * выполнять данную команду в бою и доступна ли эта команда объекту в текущем ходу.
 */
public class MeleeAttackCommandFactory
        implements BattleCommandFactory<MeleeAttackParameters, MeleeAttackCommand> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        if (model instanceof Fighter fighter) {
            return fighter.canHit() && fighter.haveOpponentsNearby(battleContext);
        }
        return false;
    }

    @Override
    public boolean isAvailableCommand(BattleModel model, BattleContext battleContext) {
        return true;
    }

    @Override
    @Nullable
    public MeleeAttackCommand createBattleCommand(BattleModel model, BattleContext battleContext) {
        if (model instanceof Fighter fighter) {
            return new MeleeAttackCommand(fighter.getNearestBattleModels(battleContext));
        }
        return null;
    }
}
