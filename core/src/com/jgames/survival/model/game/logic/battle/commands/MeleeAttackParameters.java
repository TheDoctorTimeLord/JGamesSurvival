package com.jgames.survival.model.game.logic.battle.commands;

import ru.jengine.battlemodule.core.commands.CommandExecutionParameters;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.RandomUtils;

import java.util.Collections;
import java.util.List;

/**
 * Описывает параметры команды MeleeAttackCommand
 */
public class MeleeAttackParameters implements CommandExecutionParameters {
    private final List<BattleModel> enemies;
    private BattleModel enemy;

    public MeleeAttackParameters(List<BattleModel> enemies) {
        this.enemies = (enemies != null) ? enemies : Collections.emptyList();
        appointOpponent();
    }

    /**
     * Возвращает противников
     */
    public List<BattleModel> getEnemies() {
        return enemies;
    }

    /**
     * Возвращает противника
     */
    public BattleModel getEnemy() {
        return enemy;
    }

    /**
     * Назначает противника
     */
    public void appointOpponent() {
        this.enemy = RandomUtils.chooseInCollection(enemies);
    }
}
