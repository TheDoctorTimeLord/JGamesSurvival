package com.jgames.survival.model.api.actionhandlers;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.GameAction;
import com.jgames.survival.model.api.GameActionHandler;

public class DefaultGameActionHandler implements GameActionHandler<GameAction> {
    @Override
    public Class<?> getHandlingActionClass() {
        return null;
    }

    @Override
    public void handle(GameAction action) {
        Gdx.app.error("UNRESOLVED", "Action [" + action + "] was not handled");
    }
}
