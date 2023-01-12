package com.jgames.survival.view.core.eventbussubsistems;

import java.util.Collection;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.utils.eventbus.BusEvent;
import com.jgames.survival.utils.eventbus.EventBusSubsystem;
import com.jgames.survival.utils.eventbus.EventBusSubsystemPostHandler;
import com.jgames.survival.view.core.actions.UIAction;
import com.jgames.survival.view.core.actions.UIActionManager;

@Bean
public class UIEventBusSubsystem implements EventBusSubsystem {
    private final Collection<? extends EventBusSubsystemPostHandler<? extends BusEvent>> postHandlers;

    public UIEventBusSubsystem(UIActionManager actionManager) {
        this.postHandlers = List.of(new UIActionEventBusSubsystemPostHandler(actionManager));
    }

    @Override
    public Collection<? extends EventBusSubsystemPostHandler<? extends BusEvent>> getRegisteringListeners() {
        return postHandlers;
    }

    private static class UIActionEventBusSubsystemPostHandler extends EventBusSubsystemPostHandler<UIAction> {
        private final UIActionManager actionManager;

        public UIActionEventBusSubsystemPostHandler(UIActionManager actionManager) {
            this.actionManager = actionManager;
        }

        @Override
        public void handle(UIAction event) {
            actionManager.notify(event);
        }
    }
}
