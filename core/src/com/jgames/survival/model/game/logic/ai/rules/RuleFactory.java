package com.jgames.survival.model.game.logic.ai.rules;

public interface RuleFactory {
    Rule buildRule(RuleCondition condition, RuleAction action);
}
