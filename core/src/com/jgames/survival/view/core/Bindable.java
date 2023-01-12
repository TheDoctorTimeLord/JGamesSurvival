package com.jgames.survival.view.core;

import javax.annotation.Nullable;

import com.jgames.survival.view.core.displays.Display;

public interface Bindable {
    void onBind(UIEventBus eventBus, @Nullable Display parent);
    void onUnbind(UIEventBus eventBus);

    @Nullable
    Display getParent();
}
