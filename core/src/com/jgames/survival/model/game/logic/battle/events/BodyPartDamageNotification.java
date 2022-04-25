package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

/**
 * Хранит данные: атакующий персонаж, получающий урон персонаж,
 * часть тела, которая должна быть повреждена у получающего урон персонажа
 */
public class BodyPartDamageNotification implements BattleAction {
    private final int attackerId;
    private final int targetId;
    private final String damagedBodyPart;

    public BodyPartDamageNotification(int attackerId, int targetId, String damagedBodyPart) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.damagedBodyPart = damagedBodyPart;
    }

    public BodyPartDamageNotification(BodyPartDamageEvent event) {
        this.attackerId = event.getAttackerId();
        this.targetId = event.getTargetId();
        this.damagedBodyPart = event.getDamagedBodyPart();
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
        return "BodyPartDamage {" +
                "attackerId=" + attackerId +
                ", targetId=" + targetId +
                ", damagedBodyPart=" + damagedBodyPart +
                '}';
    }
}
