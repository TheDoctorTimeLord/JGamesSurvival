package com.jgames.survival.model.game.logic.battle.commands;

import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils
        .choosedamagedbodypartstrategy.DamagedBodyPartRandomStrategy;
import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.Fighter;
import com.jgames.survival.model.game.logic.battle.events.MeleeAttackEvent;
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

    public MeleeAttackCommand(List<BattleModel> enemies) {
        this.enemies = enemies;
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
            List<BattleModel> nearEnemies = fighter.getNearestBattleModels(battleContext);
            if (nearEnemies.contains(enemy)) {
                DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();
                String bodyPart = DamagedBodyPartRandomStrategy.chooseDamagedBodyPart(enemy);
                if (bodyPart == null)
                    return;
                dispatcher.handle(new MeleeAttackEvent
                        (fighter.getId(), enemy.getId(), fighter.getMeleeDamagePoints(), bodyPart));
            }
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
