package com.jgames.survival.model.game.logic.ai.rules;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;

public interface RuleAction {
    void applyAction(RuleCheckContext ruleCheckContext, CognitionCluster cognitionCluster);
}
