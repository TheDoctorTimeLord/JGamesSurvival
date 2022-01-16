package com.jgames.survival.ui;

public interface UIComponentRegistrar {
    UIComponentRegistrar registerComponent(UIFactory component);
    UIElements createInterface();
}
