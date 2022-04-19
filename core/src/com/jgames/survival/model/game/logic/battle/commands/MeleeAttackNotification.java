package com.jgames.survival.model.game.logic.battle.commands;

import com.jgames.survival.model.game.logic.battle.events.MeleeAttackEvent;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

/**
 * Хранит данные: атакующий персонаж, получающий урон персонаж, наносимый урон,
 * часть тела, которая должна быть повреждена у получающего урон персонажа
 */
public class MeleeAttackNotification implements BattleAction {
    private final int attacker;
    private final int target;
    private final int damagePoints;
    private final String damagedBodyPart;

    public MeleeAttackNotification(int attacker, int target, int damagePoints, String damagedBodyPart) {
        this.attacker = attacker;
        this.target = target;
        this.damagePoints = damagePoints;
        this.damagedBodyPart = damagedBodyPart;
    }

    public MeleeAttackNotification(MeleeAttackEvent event) {
        this.attacker = event.getAttacker();
        this.target = event.getTarget();
        this.damagePoints = event.getDamagePoints();
        this.damagedBodyPart = event.getDamagedBodyPart();
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

    @Override
    public String toString() {
        return "MeleeAttack {" +
                "attacker=" + attacker +
                ", target=" + target +
                ", damagePoints=" + damagePoints +
                ", damagedBodyPart=" + damagedBodyPart +
                '}';
    }
}
