package com.jgames.survival.view.core;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.utils.eventbus.EventBus;
import com.jgames.survival.view.core.actions.UIActionListener;
import com.jgames.survival.view.core.actions.UIActionManager;
import com.jgames.survival.view.core.eventbussubsistems.DebugUIEventBusSubsystem;
import com.jgames.survival.view.core.events.UIEvent;

@Bean
public class UIEventBus {
    private final EventBus eventBus;
    private final UIActionManager uiActionManager;
    private final DebugUIEventBusSubsystem debugSubsystem;

    public UIEventBus(EventBus eventBus, UIActionManager uiActionManager, DebugUIEventBusSubsystem debugSubsystem) {
        this.eventBus = eventBus;
        this.uiActionManager = uiActionManager;
        this.debugSubsystem = debugSubsystem;
    }

    public void registerEvent(UIEvent event) {
        eventBus.registerEvent(event);
    }

    public void addUIActionListener(UIActionListener listener) {
        uiActionManager.addSubscriber(listener);
    }

    public void removeUIActionListener(UIActionListener listener) {
        uiActionManager.removeSubscriber(listener);
    }

    public void setEnableDebugEventInfo(boolean enable) {
        if (enable) {
            eventBus.enableSubsystem(debugSubsystem);
        } else {
            eventBus.disableSubsystem(debugSubsystem);
        }
    }
}
