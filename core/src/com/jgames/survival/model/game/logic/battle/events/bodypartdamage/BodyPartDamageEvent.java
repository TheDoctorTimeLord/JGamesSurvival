package com.jgames.survival.model.game.logic.battle.events.bodypartdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.events.BattleEvent;

/**
 * Событие, описывающее повреждение части тела
 */
public class BodyPartDamageEvent extends BattleEvent implements BattleAction {
    private final int attackerId;
    private final int targetId;
    private final String damagedBodyPart;

    public BodyPartDamageEvent(int attackerId, int targetId, String damagedBodyPart) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagedBodyPart = damagedBodyPart;
    }

    /**
     * Возвращает идентификатор атакующего персонажа
     */
    public int getAttackerId() {
        return attackerId;
    }

    /**
     * Возвращает идентификатор персонажа, получающего урон
     */
    public int getTargetId() {
        return targetId;
    }

    /**
     * Возвращает часть тела, которая будет повреждена у персонажа, получающего урон
     */
    public String getDamagedBodyPart() {
        return damagedBodyPart;
    }

    @Override
    public String toString() {
        return "BodyPartDamageEvent{" +
                "attackerId=" + attackerId +
                ", targetId=" + targetId +
                ", damagedBodyPart='" + damagedBodyPart + '\'' +
                '}';
    }
}
