package com.jgames.survival.utils.eventbus;

import com.jgames.survival.utils.ReflectionUtils;
import com.jgames.survival.utils.ReflectionUtils.LazyGenericTypesIterable;

public abstract class DebugEventBusSubsystemPostHandler<T extends BusEvent> extends EventBusSubsystemPostHandler<T> {
    @Override
    public Class<T> getHandlingEventType() {
        return (Class<T>)ReflectionUtils.getGenericInterfaceType(
                new LazyGenericTypesIterable(getClass()),
                DebugEventBusSubsystemPostHandler.class,
                0
        );
    }

    @Override
    public int getPriority() {
        return -100;
    }
}
