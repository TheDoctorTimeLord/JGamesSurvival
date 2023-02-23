package com.jgames.survival.model.game.logic.ai.cognition.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;

public class CognitionStorage {
    private final Map<String, Cognition> knowledges = new HashMap<>();

    public void addCognition(Cognition node) {
        String nodeCode = node.getCode();
        if (!knowledges.containsKey(nodeCode)) {
            knowledges.put(nodeCode, node);
        }
    }

    public void removeCognition(Cognition cognition) {
        knowledges.remove(cognition.getCode());
    }

    public boolean containsCognition(Cognition node) {
        return containsCognition(node.getCode());
    }

    public boolean containsCognition(String nodeCode) {
        return knowledges.containsKey(nodeCode);
    }

    @Nullable
    public Cognition getCognition(String nodeCode) {
        return knowledges.get(nodeCode);
    }
}
