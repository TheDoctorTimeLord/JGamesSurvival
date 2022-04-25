package com.jgames.survival.model.game.logic.battle.commands;

import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.choosedamagedbodypartstrategy.DamagedBodyPartRandomStrategy;
import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.CanHit;
import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.Fighter;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.models.BattleModel;

/**
 * Описывает фабрику ближнего боя, создающую объект команды.
 * В обязанности фабрики также входит определить может ли конкретный динамический объект
 * выполнять данную команду в бою и доступна ли эта команда объекту в текущем ходу.
 */
public class MeleeAttackCommandFactory
        implements BattleCommandFactory<MeleeAttackParameters, MeleeAttackCommand> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        if (model instanceof CanHit canHit) {
            return canHit.canHit();
        }
        return false;
    }

    @Override
    public boolean isAvailableCommand(BattleModel model, BattleContext battleContext) {
        if (model instanceof Fighter fighter) {
            return fighter.hasOpponentsNearby(battleContext);
        }
        return false;
    }

    @Override
    public MeleeAttackCommand createBattleCommand(BattleModel model, BattleContext battleContext) {
        Fighter fighter = (Fighter) model;
        return new MeleeAttackCommand(
                fighter.getNearestBattleModels(battleContext.getBattleState()),
                new DamagedBodyPartRandomStrategy());
    }
}
