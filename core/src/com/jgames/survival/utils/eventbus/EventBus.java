package com.jgames.survival.utils.eventbus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.beancontainer.annotations.PreDestroy;
import ru.jengine.eventqueue.Dispatcher;
import ru.jengine.eventqueue.event.PostHandler;

@Bean
public class EventBus {
    private final Dispatcher dispatcher;
    private final EventBusPollHandler pollHandler;
    private final Map<EventBusSubsystem, Boolean> subsystems = new HashMap<>();

    public EventBus(Dispatcher dispatcher, EventBusPollHandler pollHandler, List<EventBusSubsystem> subsystems) {
        this.dispatcher = dispatcher;
        this.pollHandler = pollHandler;

        dispatcher.registerEventPoolHandler(List.of(pollHandler), pollHandler, List.of());

        for (EventBusSubsystem subsystem : subsystems) {
            registerSubsystem(subsystem);
        }
    }

    public void registerEvent(BusEvent event) {
        dispatcher.registerEvent(event);
    }

    public void dispatchAll() {
        dispatcher.handleEvents(EventBusConstants.EVENT_BUS_HANDLER);
    }

    public void registerSubsystem(EventBusSubsystem subsystem) {
        subsystems.put(subsystem, false);
        enableSubsystem(subsystem);
    }

    public boolean enableSubsystem(EventBusSubsystem subsystem) {
        Boolean isEnabled = subsystems.get(subsystem);
        if (isEnabled == null || Boolean.TRUE.equals(isEnabled)) {
            return false;
        }

        for (PostHandler<? extends BusEvent> busListener : subsystem.getRegisteringListeners()) {
            dispatcher.registerPostHandlerToPool(EventBusConstants.EVENT_BUS_HANDLER, busListener);
        }
        subsystems.put(subsystem, true);

        return true;
    }

    public boolean disableSubsystem(EventBusSubsystem subsystem) {
        Boolean isEnabled = subsystems.get(subsystem);
        if (isEnabled == null || Boolean.FALSE.equals(isEnabled)) {
            return false;
        }

        for (PostHandler<? extends BusEvent> busListener : subsystem.getRegisteringListeners()) {
            dispatcher.removePostHandlerFromPool(EventBusConstants.EVENT_BUS_HANDLER, busListener);
        }
        subsystems.put(subsystem, false);

        return true;
    }

    @PreDestroy
    private void stopEventBus() {
        dispatcher.removeEventPoolHandler(List.of(pollHandler), pollHandler.getEventPoolCode());

        for (EventBusSubsystem subsystem : subsystems.keySet()) {
            disableSubsystem(subsystem);
        }
    }
}
