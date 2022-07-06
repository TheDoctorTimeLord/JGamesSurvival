package com.jgames.survival.model.game.logic.battle.commands.rangedattack;

import java.util.Set;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.commands.BattleCommandPriority;
import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageEvent;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageType;

public class RangedAttack implements BattleCommand<SelectionFromSetParameters<BattleModel>> {
    private static final int RANGED_ATTACK_DAMAGE = 3;

    private final Set<BattleModel> enemies;

    public RangedAttack(Set<BattleModel> enemies) {
        this.enemies = enemies;
    }

    @Override
    public SelectionFromSetParameters<BattleModel> createParametersTemplate() {
        return new SelectionFromSetParameters<>(enemies);
    }

    @Override
    public void perform(BattleModel model, BattleContext battleContext, SelectionFromSetParameters<BattleModel> executionParameters) {
        BattleModel selectedEnemy = executionParameters.getSelectedElement();

        if (RangedAttackFactory.canRangedAttack(model) && selectedEnemy != null
                && RangedAttackFactory.getVisibleEnemies(model, battleContext).contains(selectedEnemy))
        {
            battleContext.getDispatcher().handle(new DamageEvent(model.getId(), selectedEnemy.getId(), RANGED_ATTACK_DAMAGE,
                    DamageType.RANGED.name()));
        }
    }

    @Override
    public int getPriority() {
        return BattleCommandPriority.ATTACK.getPriority();
    }
}
