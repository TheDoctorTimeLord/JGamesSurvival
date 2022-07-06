package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.standardfilling.movement.CanMoved;

/**
 * Интерфейс для объекта, который может нанести удар
 */
public interface CanHit extends CanMoved {
    /**
     * Возвращает true, если объект может нанести удар, иначе false
     */
    boolean canHit();

    /**
     * Возвращает значение, характеризующее силу удара,
     * с которой персонаж может нанести вред противнику
     */
    int getMeleeDamagePoints();
}
