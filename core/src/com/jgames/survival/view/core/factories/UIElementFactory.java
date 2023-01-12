package com.jgames.survival.view.core.factories;

import java.util.Map;

import com.jgames.survival.view.core.uielements.UIElement;

public interface UIElementFactory {
    UIElement buildElement(String elementId, Map<String, Object> properties);
}
