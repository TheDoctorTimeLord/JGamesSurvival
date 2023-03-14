package com.jgames.survival.model.game.logic.ai.rules;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;

public interface Rule {
    RuleCheckContext checkRule(CognitionCluster cognitionCluster);
    void applyRule(RuleCheckContext ruleCheckContext, CognitionCluster cognitionCluster);
}
