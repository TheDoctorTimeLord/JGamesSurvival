package com.jgames.survival.view.core.uielements;

import java.util.HashMap;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.UIException;
import com.jgames.survival.view.core.UIRuntimeException;
import com.jgames.survival.view.core.factories.UIFactoringManager;

@Bean
public class UIElementManager {
    private final Map<String, UIElement> usedUiElement = new HashMap<>();
    private final UIFactoringManager uiFactoringManager;

    public UIElementManager(UIFactoringManager uiFactoringManager) {
        this.uiFactoringManager = uiFactoringManager;
    }

    public UIElement createUIElement(String uiElementTypeCode, String uiElementId, Map<String, Object> properties) {
        try {
            UIElement uiElement = uiFactoringManager.getUIElementFactory(uiElementTypeCode)
                    .buildElement(uiElementId, properties);
            return usedUiElement.put(uiElementId, uiElement);
        }
        catch (UIException e) {
            String message = "UIElement [%s, %s] can not be created".formatted(uiElementTypeCode, uiElementId);
            throw new UIRuntimeException(message, e);
        }
    }

    public UIElement getUiElementById(String uiElementId) {
        UIElement uiElement = usedUiElement.get(uiElementId);
        if (uiElement == null) {
            throw new UIRuntimeException("UIElement with code [%s] was not exist".formatted(uiElementId));
        }
        return uiElement;
    }

    public UIElement removeUiElement(String uiElementId) {
        return usedUiElement.remove(uiElementId);
    }
}
