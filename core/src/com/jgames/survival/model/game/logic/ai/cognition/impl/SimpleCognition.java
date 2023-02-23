package com.jgames.survival.model.game.logic.ai.cognition.impl;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;

public class SimpleCognition implements Cognition {
    private final String code;

    public SimpleCognition(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
