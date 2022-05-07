package com.jgames.survival.model.game.logic.battle.events.dealingdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

/**
 * Хранит данные: атакующий персонаж, получающий урон персонаж, наносимый урон
 */
public class DamageNotification implements BattleAction {
    private final int attackerId;
    private final int targetId;
    private final int damagePoints;
    private final String damageType;

    public DamageNotification(int attackerId, int targetId, int damagePoints, String damageType) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagePoints = damagePoints;
        this.damageType = damageType;
    }

    public DamageNotification(DamageEvent event) {
        this(event.getAttackerId(), event.getTargetId(), event.getDamagePoints(), event.getDamageType());
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
     * Возвращает тип наносимого урона
     */
    public String getDamageType() {
        return damageType;
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
                ", damageType=" + damageType +
                ", damagePoints=" + damagePoints +
                '}';
    }
}
