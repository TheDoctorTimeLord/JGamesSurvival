package com.jgames.survival.view.core.uielements;

import java.util.HashMap;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

@Bean
public class UIElementManager {
    private final Map<String, UIElement> usedUiElement = new HashMap<>();
}
