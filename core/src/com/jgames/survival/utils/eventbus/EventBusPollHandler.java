package com.jgames.survival.utils.eventbus;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.eventqueue.dataclasses.EventHandlersManager;
import ru.jengine.eventqueue.dataclasses.EventHandlingContext;
import ru.jengine.eventqueue.event.Event;
import ru.jengine.eventqueue.eventpool.ComplexEventPoolQueueHandler;

@Bean
public class EventBusPollHandler extends ComplexEventPoolQueueHandler {
    public EventBusPollHandler() {
        super(EventBusConstants.EVENT_BUS_HANDLER);
    }

    @Override
    protected EventHandlersManager prepareEventHandlersManager(EventHandlingContext context) {
        return new HierarchicalEventHandlerManager(context.getPreHandlers());
    }

    @Override
    public boolean isValid(Event event) {
        return event instanceof BusEvent;
    }
}
