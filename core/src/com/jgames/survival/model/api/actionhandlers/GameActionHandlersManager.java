package com.jgames.survival.model.api.actionhandlers;

import java.util.List;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.jgames.survival.model.api.GameAction;
import com.jgames.survival.model.api.GameActionHandler;

@Bean
public class GameActionHandlersManager {
    private static final DefaultGameActionHandler DEFAULT_GAME_ACTION_HANDLER = new DefaultGameActionHandler();
    private final Map<Class<?>, GameActionHandler<?>> actionHandlers;

    public GameActionHandlersManager(List<GameActionHandler<?>> actionHandlers) {
        Builder<Class<?>, GameActionHandler<?>> builder = ImmutableMap.builder();

        for (GameActionHandler<?> actionHandler : actionHandlers) {
            builder.put(actionHandler.getHandlingActionClass(), actionHandler);
        }

        this.actionHandlers = builder.build();
    }

    @SuppressWarnings("unchecked")
    public GameActionHandler<GameAction> findHandler(GameAction action) {
        return (GameActionHandler<GameAction>)actionHandlers.getOrDefault(action.getClass(), DEFAULT_GAME_ACTION_HANDLER);
    }
}
