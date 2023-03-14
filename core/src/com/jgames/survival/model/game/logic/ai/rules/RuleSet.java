package com.jgames.survival.model.game.logic.ai.rules;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;

public interface RuleSet {
    void addRule(Rule rule);
    void removeRule(Rule rule);

    void applyRules(CognitionCluster clusterToCheck, CognitionCluster clusterToApply);
}
