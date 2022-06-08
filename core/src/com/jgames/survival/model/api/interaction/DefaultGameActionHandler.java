package com.jgames.survival.model.api.interaction;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.game.logic.GameBattleHandler;

public class DefaultGameActionHandler implements GameActionHandler<GameAction> {
    @Override
    public void configure(GameBattleHandler battleHandler) {}

    @Override
    public Class<?> getHandlingActionClass() {
        return null;
    }

    @Override
    public void handle(GameAction action) {
        Gdx.app.error("UNRESOLVED", "Action [" + action + "] was not handled");
    }
}
