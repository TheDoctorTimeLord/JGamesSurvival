package com.jgames.survival.model.api.actions;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.GameActionHandler;
import com.jgames.survival.model.game.logic.GameBattleHandler;

@Bean
public class NewTurnBattleActionHandler implements GameActionHandler<NewTurnBattleAction> {
    private GameBattleHandler battleHandler;

    @Override
    public void configure(GameBattleHandler battleHandler) {
        this.battleHandler = battleHandler;
    }

    @Override
    public Class<?> getHandlingActionClass() {
        return NewTurnBattleAction.class;
    }

    @Override
    public void handle(NewTurnBattleAction action) {
        battleHandler.getBattleMaster().takeTurn();
    }
}
