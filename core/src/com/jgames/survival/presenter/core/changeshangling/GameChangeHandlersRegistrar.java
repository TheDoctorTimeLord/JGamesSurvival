package com.jgames.survival.presenter.core.changeshangling;

import com.jgames.survival.model.api.GameChangeListenerRegistrar;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;

public class GameChangeHandlersRegistrar {
    private final GameChangeListenerRegistrar registrar;
    private final PresentingGameState gameState;

    public GameChangeHandlersRegistrar(GameChangeListenerRegistrar registrar, PresentingGameState gameState) {
        this.registrar = registrar;
        this.gameState = gameState;
    }

    public GameChangeHandlersRegistrar registerGameChangeHandler(GameChangeHandler handler) {
        handler.setGameState(gameState);
        registrar.addGameChangesListener(handler);

        return this;
    }

    public GameChangeHandlersRegistrar unregisterGameChangeHandler(GameChangeHandler handler) {
        handler.setGameState(null);
        registrar.removeGameChangeListener(handler);

        return this;
    }
}
