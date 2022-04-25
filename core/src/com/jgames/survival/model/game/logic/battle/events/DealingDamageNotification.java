package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

/**
 * Хранит данные: атакующий персонаж, получающий урон персонаж, наносимый урон
 */
public class DealingDamageNotification implements BattleAction {
    private final int attackerId;
    private final int targetId;
    private final int damagePoints;

    public DealingDamageNotification(int attackerId, int targetId, int damagePoints) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagePoints = damagePoints;
    }

    public DealingDamageNotification(DealingDamageEvent event) {
        this.attackerId = event.getAttackerId();
        this.targetId = event.getTargetId();
        this.damagePoints = event.getDamagePoints();
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

    @Override
    public String toString() {
        return "DealingDamage {" +
                "attackerId=" + attackerId +
                ", targetId=" + targetId +
                ", damagePoints=" + damagePoints +
                '}';
    }
}
