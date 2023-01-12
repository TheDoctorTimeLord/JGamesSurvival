package com.jgames.survival.utils.eventbus;

import ru.jengine.eventqueue.event.PostHandler;

import com.jgames.survival.utils.ReflectionUtils;
import com.jgames.survival.utils.ReflectionUtils.LazyGenericTypesIterable;

public abstract class EventBusSubsystemPostHandler<T extends BusEvent> implements PostHandler<T> {
    @Override
    public Class<T> getHandlingEventType() {
        return (Class<T>)ReflectionUtils.getGenericInterfaceType(
                new LazyGenericTypesIterable(getClass()),
                EventBusSubsystemPostHandler.class,
                0
        );
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
