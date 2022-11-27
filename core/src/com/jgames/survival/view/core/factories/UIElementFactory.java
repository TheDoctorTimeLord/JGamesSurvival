package com.jgames.survival.view.core.factories;

import java.util.Map;

import com.jgames.survival.view.core.uielements.UIElement;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public interface UIElementFactory {
    UIElement buildElement(String elementId, Map<String, Object> properties);
    UIElementWrapper buildElementWrapper(UIElement wrapped, Map<String, Object> properties);
}
