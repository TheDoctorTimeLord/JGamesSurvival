package com.jgames.survival.model.game.logic.battle.models;

import java.util.List;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.state.BattleState;
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
     * Проверяет наличие противников рядом с бойцом
     * @param battleContext контекст битвы
     * @return true, если рядом есть противники, иначе false
     */
    boolean hasOpponentsNearby(BattleContext battleContext);

    /**
     * Возвращает персонажей, которые находятся рядом с бойцом
     * @param battleState состояние битвы
     */
    List<BattleModel> getNearestBattleModels(BattleState battleState);

    /**
     * Возвращает значение, характеризующее силу удара,
     * с которой персонаж может нанести вред противнику
     */
    int getMeleeDamagePoints();
}
