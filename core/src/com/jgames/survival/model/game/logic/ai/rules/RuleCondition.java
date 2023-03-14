package com.jgames.survival.model.game.logic.ai.rules;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;

public interface RuleCondition {
    void applyCondition(CognitionCluster cognitionCluster, RuleCheckContext currentContext);
}
