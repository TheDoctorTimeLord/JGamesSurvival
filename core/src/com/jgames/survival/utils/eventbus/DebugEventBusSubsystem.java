package com.jgames.survival.utils.eventbus;

import java.util.Collection;

public interface DebugEventBusSubsystem extends EventBusSubsystem {
    @Override
    Collection<DebugEventBusSubsystemPostHandler<? extends BusEvent>> getRegisteringListeners();
}
