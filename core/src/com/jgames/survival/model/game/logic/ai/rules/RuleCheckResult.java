package com.jgames.survival.model.game.logic.ai.rules;

import java.util.List;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;

public class RuleCheckResult {
    private final List<Cognition> foundCognition;

    public RuleCheckResult(List<Cognition> foundCognition) {
        this.foundCognition = foundCognition;
    }

    public List<Cognition> getFoundCognition() {
        return foundCognition;
    }

    public boolean isRuleNeedApply() {
        return !foundCognition.isEmpty();
    }
}
