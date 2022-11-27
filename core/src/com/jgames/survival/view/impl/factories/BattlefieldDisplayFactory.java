package com.jgames.survival.view.impl.factories;

import java.util.Map;

import com.jgames.survival.view.core.factories.DisplayFactory;
import com.jgames.survival.view.impl.displays.BattlefieldDisplay;

public class BattlefieldDisplayFactory implements DisplayFactory<BattlefieldDisplay> {
    public static final String SCALE_POINT_TO_WINDOW = "scalePointToWindow";

    @Override
    public BattlefieldDisplay buildDisplay(Map<String, Object> properties) {
        float scalePointToWindow = getPropertyOrDefault(SCALE_POINT_TO_WINDOW, properties, 1f);

        return new BattlefieldDisplay(scalePointToWindow);
    }
}
