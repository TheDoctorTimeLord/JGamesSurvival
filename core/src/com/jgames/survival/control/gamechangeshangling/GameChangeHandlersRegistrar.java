package com.jgames.survival.control.gamechangeshangling;

import com.jgames.survival.model.api.GameChangeListenerRegistrar;

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
}
