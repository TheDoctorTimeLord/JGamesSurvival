package com.jgames.survival.view.core.eventbussubsistems;

import java.util.Collection;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.jgames.survival.utils.eventbus.BusEvent;
import com.jgames.survival.utils.eventbus.DebugEventBusSubsystem;
import com.jgames.survival.utils.eventbus.DebugEventBusSubsystemPostHandler;
import com.jgames.survival.view.core.actions.UIAction;
import com.jgames.survival.view.core.events.UIEvent;

@Bean
public class DebugUIEventBusSubsystem implements DebugEventBusSubsystem {
    private final Collection<DebugEventBusSubsystemPostHandler<? extends BusEvent>> listeners;

    public DebugUIEventBusSubsystem(Logger logger) {
        this.listeners = List.of(
                new DebugEventBusSubsystemPostHandler<UIAction>() {
                    @Override
                    public void handle(UIAction event) {
                        logger.log("DebugUIEventBus: UIAction", event.toString());
                    }
                },
                new DebugEventBusSubsystemPostHandler<UIEvent>() {
                    @Override
                    public void handle(UIEvent event) {
                        logger.log("DebugUIEventBus: UIEvent", event.toString());
                    }
                }
        );
    }

    @Override
    public Collection<DebugEventBusSubsystemPostHandler<? extends BusEvent>> getRegisteringListeners() {
        return listeners;
    }
}
