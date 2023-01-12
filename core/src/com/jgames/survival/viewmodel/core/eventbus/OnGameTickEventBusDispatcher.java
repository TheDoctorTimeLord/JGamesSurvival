package com.jgames.survival.viewmodel.core.eventbus;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.utils.eventbus.EventBus;
import com.jgames.survival.viewmodel.core.UpdatableOnGameTick;

@Bean
public class OnGameTickEventBusDispatcher implements UpdatableOnGameTick {
    private final EventBus eventBus;

    public OnGameTickEventBusDispatcher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void update() {
        eventBus.dispatchAll();
    }
}
