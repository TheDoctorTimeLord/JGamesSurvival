package com.jgames.survival.view.core.factories;

import java.util.Map;

import com.jgames.survival.view.core.uielements.UIElement;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public interface UIElementFactory {
    UIElement buildElement(Map<String, Object> properties);
    UIElementWrapper buildElementWithWrapping(Map<String, Object> properties);
}
