package com.jgames.survival.model.game.logic.battle.commands;

import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils
        .choosedamagedbodypartstrategy.ChooseDamagedBodyPartStrategy;
import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.Fighter;
import com.jgames.survival.model.game.logic.battle.events.BodyPartDamageEvent;
import com.jgames.survival.model.game.logic.battle.events.DealingDamageEvent;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.models.BattleModel;

import java.util.List;

/**
 * Описывает команду ближнего боя, которую будет исполнять динамический объект.
 */
public class MeleeAttackCommand implements BattleCommand<MeleeAttackParameters> {
    private final List<BattleModel> enemies;
    private final ChooseDamagedBodyPartStrategy chooseDamagedBodyPartStrategy;

    public MeleeAttackCommand(List<BattleModel> enemies, ChooseDamagedBodyPartStrategy strategy) {
        this.enemies = enemies;
        this.chooseDamagedBodyPartStrategy = strategy;
    }

    @Override
    public MeleeAttackParameters createParametersTemplate() {
        return new MeleeAttackParameters(enemies);
    }

    @Override
    public void perform(BattleModel model, BattleContext battleContext,
                        MeleeAttackParameters executionParameters) {
        if (model instanceof Fighter fighter) {
            BattleModel enemy = executionParameters.getEnemy();
            List<BattleModel> nearEnemies = fighter.getNearestBattleModels(battleContext.getBattleState());
            if (nearEnemies.contains(enemy)) {
                DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();
                String bodyPart = chooseDamagedBodyPartStrategy.chooseDamagedBodyPart(enemy);
                if (bodyPart == null || enemy == null)
                    return;
                dispatcher.handle(new BodyPartDamageEvent(fighter.getId(), enemy.getId(), bodyPart));
                dispatcher.handle(new DealingDamageEvent(
                        fighter.getId(), enemy.getId(), fighter.getMeleeDamagePoints()));
            }
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
