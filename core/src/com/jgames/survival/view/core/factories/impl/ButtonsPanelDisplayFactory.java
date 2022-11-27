package com.jgames.survival.view.core.factories.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import ru.jengine.utils.Logger;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.jgames.survival.view.core.displays.impl.ButtonsPanelDisplay.ButtonsAdditionalParameters;
import com.jgames.survival.view.core.factories.DisplayFactory;
import com.jgames.survival.view.core.factories.impl.ButtonGenerator.ButtonConfiguration;
import com.jgames.survival.view.core.displays.impl.ButtonsPanelDisplay;

public class ButtonsPanelDisplayFactory implements DisplayFactory<ButtonsPanelDisplay> {
    public static final String AVAILABLE_BUTTONS = "availableButtons";
    public static final String VERTICAL = "vertical";
    public static final String BUTTON_PADDING = "buttonPadding";

    private final Map<String, Supplier<Button>> configuredButtons = new HashMap<>();
    private final Logger logger;

    public ButtonsPanelDisplayFactory(List<ButtonGenerator> configurations, Logger logger) {
        this.logger = logger;

        for (ButtonGenerator configuration : configurations) {
            ButtonConfiguration buttonConfiguration = configuration.generate();
            configuredButtons.put(buttonConfiguration.buttonName, buttonConfiguration.buttonGenerator);
        }
    }

    @Override
    public ButtonsPanelDisplay buildDisplay(Map<String, Object> properties) {
        List<String> availableButtons = getProperty(AVAILABLE_BUTTONS, properties);
        boolean vertical = getPropertyOrDefault(VERTICAL, properties, false);

        List<Button> buttons = new ArrayList<>(availableButtons.size());
        for (String availableButtonName : availableButtons) {
            Button button = configuredButtons.get(availableButtonName).get();
            if (button == null) {
                logger.error("ButtonsPanelDisplayFactory", "Buttons with name [%s] was not found".formatted(availableButtonName));
            } else {
                button.setName(availableButtonName);
                buttons.add(button);
            }
        }

        return new ButtonsPanelDisplay(buttons, vertical, new ButtonsAdditionalParameters()
                .buttonPadding(getPropertyOrDefault(BUTTON_PADDING, properties, 0f))
        );
    }
}
