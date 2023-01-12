package com.jgames.survival.view.core.events;

import java.util.Arrays;

public class UIComponentClicked extends UIEvent {
    private final String[] componentPath;
    private final String componentType;
    private final String componentName;

    public UIComponentClicked(String[] componentPath, String componentType, String componentName) {
        this.componentPath = componentPath;
        this.componentType = componentType;
        this.componentName = componentName;
    }

    public String getComponentName() {
        return componentName;
    }

    public String[] getComponentPath() {
        return componentPath;
    }

    public String getComponentType() {
        return componentType;
    }

    @Override
    public String toString() {
        return "(%s) %s > %s".formatted(componentType, String.join(".", Arrays.asList(componentPath)), componentName);
    }
}
