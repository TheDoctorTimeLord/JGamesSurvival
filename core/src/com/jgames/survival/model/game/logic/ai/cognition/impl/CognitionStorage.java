package com.jgames.survival.model.game.logic.ai.cognition.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;
import com.jgames.survival.utils.Multimap;

public class CognitionStorage {
    private final Multimap<String, Cognition> knowledge = new Multimap<>();

    public void addCognition(Cognition cognition) {
        knowledge.add(cognition.getCode(), cognition);
    }

    public void removeCognition(Cognition cognition) {
        String cognitionCode = cognition.getCode();

        Set<Cognition> cognitionWithSameCode = knowledge.get(cognitionCode);
        if (cognitionWithSameCode == null) {
            return;
        }

        cognitionWithSameCode.remove(cognition);
        if (cognitionWithSameCode.isEmpty()) {
            knowledge.removeKey(cognitionCode);
        }
    }

    public boolean containsCognition(Cognition cognition) {
        Set<Cognition> cognitionWithSameCode = knowledge.get(cognition.getCode());
        return cognitionWithSameCode != null && cognitionWithSameCode.contains(cognition);
    }

    public boolean containsCognition(String cognitionCode) {
        return knowledge.get(cognitionCode) != null;
    }

    public Collection<Cognition> getCognition(String cognitionCode) {
        return knowledge.asMap().getOrDefault(cognitionCode, Collections.emptySet());
    }
}
