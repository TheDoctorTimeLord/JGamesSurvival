package com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter;

/**
 * Интерфейс для объекта, который может нанести удар
 */
public interface CanHit {
    /**
     * Возвращает true, если объект может нанести удар, иначе false
     */
    boolean canHit();
}
