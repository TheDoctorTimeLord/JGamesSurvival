package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.events.BattleEvent;

/**
 * Событие, описывающее ближнюю атаку
 */
public class MeleeAttackEvent extends BattleEvent {
    private final int attacker;
    private final int target;
    private final int damagePoints;
    private final String damagedBodyPart;

    public MeleeAttackEvent(int attacker, int target, int damagePoints, String damagedBodyPart) {
        this.attacker = attacker;
        this.target = target;
        this.damagePoints = damagePoints;
        this.damagedBodyPart = damagedBodyPart;
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
    public int getAttacker() {
        return attacker;
    }

    /**
     * Возвращает идентификатор персонажа, получающего урон
     */
    public int getTarget() {
        return target;
    }

    /**
     * Возвращает часть тела, которая будет повреждена у персонажа, получающего урон
     */
    public String getDamagedBodyPart() {
        return damagedBodyPart;
    }
}
