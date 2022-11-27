package com.jgames.survival.view.core.factories;

import java.util.Map;

import com.jgames.survival.view.core.displays.Display;

public interface DisplayFactory<D extends Display> {
    D buildDisplay(Map<String, Object> properties);

    @SuppressWarnings("unchecked")
    default <T> T getProperty(String property, Map<String, Object> properties) {
        return (T)properties.get(property);
    }

    @SuppressWarnings("unchecked")
    default <T> T getPropertyOrDefault(String property, Map<String, Object> properties, T defaultValue) {
        return (T)properties.getOrDefault(property, defaultValue);
    }
}
