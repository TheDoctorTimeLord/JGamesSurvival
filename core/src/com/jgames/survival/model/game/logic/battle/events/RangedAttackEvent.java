package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.events.BattleEvent;

public class RangedAttackEvent extends BattleEvent implements BattleAction {
    private final int attackerId;
    private final int targetId;
    private final int damagePoints;

    public RangedAttackEvent(int attackerId, int targetId, int damagePoints) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagePoints = damagePoints;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    public int getTargetId() {
        return targetId;
    }

    @Override
    public String toString() {
        return "RangedAttackEvent{" +
                "attackerId=" + attackerId +
                ", targetId=" + targetId +
                ", damagePoints=" + damagePoints +
                '}';
    }
}
