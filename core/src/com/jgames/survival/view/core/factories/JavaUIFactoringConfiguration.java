package com.jgames.survival.view.core.factories;

import java.util.HashMap;
import java.util.Map;

import com.jgames.survival.view.core.UIRuntimeException;

public class JavaUIFactoringConfiguration implements UIFactoringConfiguration {
    private final Map<String, DisplayFactory<?>> displayFactories;
    private final Map<String, UIElementFactory> uiElementFactories;

    private JavaUIFactoringConfiguration(Map<String, DisplayFactory<?>> displayFactories,
            Map<String, UIElementFactory> uiElementFactories)
    {
        this.displayFactories = displayFactories;
        this.uiElementFactories = uiElementFactories;
    }

    @Override
    public void configure(UIFactoringManager manager) {
        displayFactories.forEach(manager::addDisplayFactory);
        uiElementFactories.forEach(manager::addUIElementFactory);
    }

    public static ConfigurationBuilder create() {
        return new ConfigurationBuilder();
    }

    public static class ConfigurationBuilder {
        private final Map<String, DisplayFactory<?>> displayFactories = new HashMap<>();
        private final Map<String, UIElementFactory> uiElementFactories = new HashMap<>();

        private ConfigurationBuilder() {}

        public ConfigurationBuilder addDisplayFactory(String factoringCode, DisplayFactory<?> factory) {
            if (displayFactories.containsKey(factoringCode)) {
                throw new UIRuntimeException("Display factory with code [%s] was registered already".formatted(factoringCode));
            }
            displayFactories.put(factoringCode, factory);
            return this;
        }

        public ConfigurationBuilder addUIElementFactory(String factoringCode, UIElementFactory factory) {
            if (uiElementFactories.containsKey(factoringCode)) {
                throw new UIRuntimeException("UI element factory with code [%s] was registered already".formatted(factoringCode));
            }
            uiElementFactories.put(factoringCode, factory);
            return this;
        }

        public JavaUIFactoringConfiguration build() {
            return new JavaUIFactoringConfiguration(displayFactories, uiElementFactories);
        }
    }
}
