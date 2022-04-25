package com.jgames.survival.model.game.logic.battle.commands;

import ru.jengine.battlemodule.core.commands.CommandExecutionParameters;
import ru.jengine.battlemodule.core.models.BattleModel;

import javax.annotation.Nullable;
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
    }

    /**
     * Возвращает противников, доступных для выбора
     */
    public List<BattleModel> getEnemies() {
        return enemies;
    }

    /**
     * Возвращает выбранного противника
     */
    @Nullable
    public BattleModel getEnemy() {
        return enemy;
    }

    /**
     * Назначает противника
     */
    public void selectEnemy(BattleModel targetModel) {
        this.enemy = targetModel;
    }
}
