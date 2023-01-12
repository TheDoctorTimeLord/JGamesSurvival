package com.jgames.survival.view.core.displays.impl;

import javax.annotation.Nullable;

import com.jgames.survival.view.core.ViewException;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.UIEventBus;

public abstract class BaseDisplay implements Display {
    private final String displayName;
    //TODO подумать над выносом в отдельный подкласс BaseBindingDisplay (нужна только при бинде)
    private UIEventBus eventBus;
    private Display parent;

    protected BaseDisplay(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getName() {
        return displayName;
    }

    @Override
    public void onBind(UIEventBus eventBus, @Nullable Display parent) {
        this.eventBus = eventBus;
        this.parent = parent;
    }

    @Override
    public void onUnbind(UIEventBus eventBus) {
        this.eventBus = null;
        this.parent = null;
    }

    @Nullable
    @Override
    public Display getParent() {
        return parent;
    }

    protected UIEventBus getEventBus() {
        if (eventBus == null) {
            throw new ViewException("Display [%s : %s] was not bound or skip invoke method BaseDisplay#onBind"
                    .formatted(getClass().getName(), getName()));
        }
        return eventBus;
    }
}
