package com.jgames.survival.model.game.logic.battle.models;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;

/**
 * Интерфейс для объекта, который может жить
 */
public interface HasHealth {
    /**
     * Возвращает текущее значение здоровья объекта
     */
    int getHealth();

    /**
     * Назначение нового значения здоровья объекта
     * @param damagePoints урон, который нанесли объекту
     */
    void damage(int damagePoints, @Nullable DispatcherBattleWrapper dispatcher);

    /**
     * Возвращает true, если объект жив, иначе false
     */
    boolean isDead();
}
