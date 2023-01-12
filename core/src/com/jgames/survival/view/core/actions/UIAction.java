package com.jgames.survival.view.core.actions;

import com.jgames.survival.utils.eventbus.BusEvent;
import com.jgames.survival.view.core.CanBeActor;

public abstract class UIAction extends BusEvent {
    private final CanBeActor receiver;

    protected UIAction(CanBeActor receiver) {
        this.receiver = receiver;
    }

    public CanBeActor getReceiver() {
        return receiver;
    }
}
