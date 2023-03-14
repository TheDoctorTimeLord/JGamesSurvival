package com.jgames.survival.model.game.logic.ai.rules.impl;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;
import com.jgames.survival.model.game.logic.ai.rules.Rule;
import com.jgames.survival.model.game.logic.ai.rules.RuleAction;
import com.jgames.survival.model.game.logic.ai.rules.RuleCheckContext;
import com.jgames.survival.model.game.logic.ai.rules.RuleCondition;

public class ProductionRule implements Rule {
    private final RuleCondition condition;
    private final RuleAction action;

    public ProductionRule(RuleCondition condition, RuleAction action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public RuleCheckContext checkRule(CognitionCluster cognitionCluster) {
        RuleCheckContext context = new RuleCheckContext();
        condition.applyCondition(cognitionCluster, context);
        return context;
    }

    @Override
    public void applyRule(RuleCheckContext ruleCheckContext, CognitionCluster cognitionCluster) {
        action.applyAction(ruleCheckContext, cognitionCluster);
    }
}
