package com.jgames.survival.model.game.logic.battle.commands.rangedattack;

import java.util.Set;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.events.RangedAttackEvent;

public class RangedAttack implements BattleCommand<RangedAttackParameters> {
    private static final int RANGED_ATTACK_DAMAGE = 3;

    private final Set<BattleModel> enemies;

    public RangedAttack(Set<BattleModel> enemies) {
        this.enemies = enemies;
    }

    @Override
    public RangedAttackParameters createParametersTemplate() {
        return new RangedAttackParameters(enemies);
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public void perform(BattleModel model, BattleContext battleContext, RangedAttackParameters executionParameters) {
        BattleModel selectedEnemy = executionParameters.getSelectedEnemy();

        if (RangedAttackFactory.canRangedAttack(model) && selectedEnemy != null
                && RangedAttackFactory.getVisibleEnemies(model, battleContext).contains(selectedEnemy))
        {
            battleContext.getDispatcher().handle(new RangedAttackEvent(model.getId(), selectedEnemy.getId(), RANGED_ATTACK_DAMAGE));
        }
    }
}
