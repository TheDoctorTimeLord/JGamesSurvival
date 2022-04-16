package com.jgames.survival.model.game.logic.attributes.characterStringAttributes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Константы состояния атрибутов
 */
public interface StateConstants {
    String STATE = "state";
    String GOOD = "good";
    String DAMAGED = "damaged";
    String DESTROYED = "destroyed";
    String FAR = "far";
    String NEAR = "near";
    String NONE = "none";

    Map<String, Integer> damageStrength = new LinkedHashMap<>(
            Map.of(
                    GOOD, 2,
                    DAMAGED, 1,
                    DESTROYED, 0
            )
    );

    Map<String, Integer> visionDistance = new LinkedHashMap<>(
            Map.of(
                    FAR, 2,
                    NEAR, 1,
                    NONE, 0
            )
    );
}
