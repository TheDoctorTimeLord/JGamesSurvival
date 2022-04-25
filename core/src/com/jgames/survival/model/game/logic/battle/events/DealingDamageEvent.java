package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.events.BattleEvent;

/**
 * Событие, описывающее нанесение урона
 */
public class DealingDamageEvent extends BattleEvent {
    private final int attackerId;
    private final int targetId;
    private final int damagePoints;

    public DealingDamageEvent(int attackerId, int targetId, int damagePoints) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagePoints = damagePoints;
    }

    /**
     * Возвращает наносимый урон
     */
    public int getDamagePoints() {
        return damagePoints;
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
}
