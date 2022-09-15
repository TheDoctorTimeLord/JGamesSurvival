package com.jgames.survival.viewmodel.core.changeshangling;

import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.interaction.GameChangeListenerRegistrar;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;

@Bean
public class GameChangeHandlersRegistrar {
    private final GameChangeListenerRegistrar registrar;
    private final PresentingGameState gameState;

    public GameChangeHandlersRegistrar(GameChangeListenerRegistrar registrar, PresentingGameState gameState,
            List<GameChangeHandler> gameChangeHandlers)
    {
        this.registrar = registrar;
        this.gameState = gameState;

        gameChangeHandlers.forEach(this::registerGameChangeHandler);
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
