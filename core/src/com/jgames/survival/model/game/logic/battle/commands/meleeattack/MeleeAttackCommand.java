package com.jgames.survival.model.game.logic.battle.commands.meleeattack;

import java.util.List;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.commands.BattleCommandPriority;
import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageEvent;
import com.jgames.survival.model.game.logic.battle.commands.meleeattack.meleeattackstrategies.ChooseDamagedBodyPartStrategy;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageType;
import com.jgames.survival.model.game.logic.battle.models.CanHit;

/**
 * Описывает команду ближнего боя, которую будет исполнять динамический объект.
 */
public class MeleeAttackCommand implements BattleCommand<SelectionFromSetParameters<BattleModel>> {
    private final Set<BattleModel> enemies;

    public MeleeAttackCommand(Set<BattleModel> enemies) {
        this.enemies = enemies;
        this.chooseDamagedBodyPartStrategy = strategy;
    }

    @Override
    public SelectionFromSetParameters<BattleModel> createParametersTemplate() {
        return new SelectionFromSetParameters<>(enemies);
    }

    @Override
    public void perform(BattleModel model, BattleContext battleContext, SelectionFromSetParameters<BattleModel> executionParameters) {
        if (model instanceof CanHit canHit) {
            BattleModel enemy = executionParameters.getSelectedElement();
            Set<BattleModel> nearEnemies = MeleeAttackCommandFactory
                    .getNearestBattleModels(canHit, battleContext.getBattleState());
            if (nearEnemies.contains(enemy)) {
                DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();
                String bodyPart = chooseDamagedBodyPartStrategy.chooseDamagedBodyPart(enemy);
                if (bodyPart == null || enemy == null)
                    return;
                dispatcher.handle(new BodyPartDamageEvent(model.getId(), enemy.getId(), bodyPart));
                dispatcher.handle(new DamageEvent(model.getId(), enemy.getId(), canHit.getMeleeDamagePoints(),
                        DamageType.MELEE.name()));
            }
        }
    }

    @Override
    public int getPriority() {
        return BattleCommandPriority.ATTACK.getPriority();
    }
}
