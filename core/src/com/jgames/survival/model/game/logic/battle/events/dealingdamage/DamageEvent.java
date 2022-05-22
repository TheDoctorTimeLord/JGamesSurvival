package com.jgames.survival.model.game.logic.battle.events.dealingdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.events.BattleEvent;

/**
 * Событие, описывающее нанесение урона
 */
public class DamageEvent extends BattleEvent implements BattleAction {
    private final int attackerId;
    private final int targetId;
    private final int damagePoints;
    private final String damageType;

    public DamageEvent(int attackerId, int targetId, int damagePoints, String damageType) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagePoints = damagePoints;
        this.damageType = damageType;
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
        return "DamageEvent{" +
                "attackerId=" + attackerId +
                ", targetId=" + targetId +
                ", damagePoints=" + damagePoints +
                ", damageType='" + damageType + '\'' +
                '}';
    }
}
