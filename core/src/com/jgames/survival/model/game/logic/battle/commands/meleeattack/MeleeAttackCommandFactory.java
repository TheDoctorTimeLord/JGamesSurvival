package com.jgames.survival.model.game.logic.battle.commands.meleeattack;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.commands.meleeattack.meleeattackstrategies.ChooseDamagedBodyPartStrategy;
import com.jgames.survival.model.game.logic.battle.models.CanHit;
import com.jgames.survival.model.game.logic.battle.commands.meleeattack.meleeattackstrategies.DamagedBodyPartRandomStrategy;

/**
 * Описывает фабрику ближнего боя, создающую объект команды.
 * В обязанности фабрики также входит определить может ли конкретный динамический объект
 * выполнять данную команду в бою и доступна ли эта команда объекту в текущем ходу.
 */
@Bean
public class MeleeAttackCommandFactory implements BattleCommandFactory<MeleeAttackParameters, MeleeAttackCommand> {
    private static final ChooseDamagedBodyPartStrategy DAMAGED_BODY_PART_STRATEGY = new DamagedBodyPartRandomStrategy();

    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return model instanceof CanHit canHit && canHit.canHit();
    }

    @Override
    public boolean isAvailableCommand(BattleModel model, BattleContext battleContext) {
        return model instanceof CanHit canHit && canHit.hasOpponentsNearby(battleContext.getBattleState());
    }

    @Override
    public MeleeAttackCommand createBattleCommand(BattleModel model, BattleContext battleContext) {
        CanHit canHit = (CanHit) model;
        return new MeleeAttackCommand(
                canHit.getNearestBattleModels(battleContext.getBattleState()),
                DAMAGED_BODY_PART_STRATEGY);
    }
}
