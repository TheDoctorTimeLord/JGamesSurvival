package com.jgames.survival.model.game.logic.ai.rules.impl;

import java.util.ArrayList;
import java.util.List;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;
import com.jgames.survival.model.game.logic.ai.rules.Rule;
import com.jgames.survival.model.game.logic.ai.rules.RuleCheckContext;
import com.jgames.survival.model.game.logic.ai.rules.RuleSet;

public class SerialRuleSet implements RuleSet {
    private final List<Rule> rules = new ArrayList<>();

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public void removeRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public void applyRules(CognitionCluster clusterToCheck, CognitionCluster clusterToApply) {
        for (Rule rule : rules) {
            RuleCheckContext ruleCheckContext = rule.checkRule(clusterToCheck);
            if (ruleCheckContext.isRuleCanApply()) {
                rule.applyRule(ruleCheckContext, clusterToApply);
            }
        }
    }
}
