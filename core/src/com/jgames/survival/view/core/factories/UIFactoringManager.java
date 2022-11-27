package com.jgames.survival.view.core.factories;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.UIException;
import com.jgames.survival.view.core.UIRuntimeException;
import com.jgames.survival.view.core.displays.Display;

@Bean
public class UIFactoringManager {
    private final Map<String, DisplayFactory<?>> displayFactories = new HashMap<>();
    private final Map<String, UIElementFactory> uiElementFactories = new HashMap<>();

    public void configure(UIFactoringConfiguration configuration) {
        configuration.configure(this);
    }

    public void addDisplayFactory(String factoringCode, DisplayFactory<?> factory) {
        displayFactories.put(factoringCode, factory);
    }

    public void addUIElementFactory(String factoringCode, UIElementFactory factory) {
        uiElementFactories.put(factoringCode, factory);
    }

    public <D extends Display> DisplayFactory<D> getDisplayFactory(String factoringCode) throws UIException {
        try {
            return getOrDefaultDisplayFactory(factoringCode, () -> {
                throw new UIRuntimeException("Display factory with code [%s] was not registered".formatted(factoringCode));
            });
        } catch (UIRuntimeException e) {
            throw new UIException(e);
        }
    }

    public <D extends Display> DisplayFactory<D> getOrDefaultDisplayFactory(String factoringCode,
            Supplier<DisplayFactory<D>> defaultFactorySupplier) {
        DisplayFactory<?> factory = displayFactories.get(factoringCode);
        return factory != null ? (DisplayFactory<D>)factory : defaultFactorySupplier.get();
    }

    public UIElementFactory getUIElementFactory(String factoringCode) throws UIException {
        UIElementFactory factory = uiElementFactories.get(factoringCode);
        if (factory == null) {
            throw new UIException("UI element factory with code [%s] was not registered".formatted(factoringCode));
        }
        return factory;
    }
}
