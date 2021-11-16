package com.jgames.survival.ui;

public interface UIComponentRegistrar {
    void registerComponent(UIComponent component);
    UIElements createInterface();
}
