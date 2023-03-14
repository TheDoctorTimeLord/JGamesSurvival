package com.jgames.survival.model.game.logic.ai.rules;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;

public class RuleCheckContext {
    private final Map<String, Collection<Cognition>> ruleVariables = new HashMap<>();
    private boolean isRuleCanApply = false;

    public Collection<Cognition> getRuleVariable(String variableName) {
        return ruleVariables.getOrDefault(variableName, Collections.emptySet());
    }

    public void putRuleVariable(String variableName, Collection<Cognition> cognition) {
        ruleVariables.put(variableName, cognition);
    }

    public boolean containsVariable(String variableName) {
        return ruleVariables.containsKey(variableName);
    }

    public void setRuleCanApply(boolean isRuleCanApply) {
        this.isRuleCanApply = isRuleCanApply;
    }

    public boolean isRuleCanApply() {
        return !isRuleCanApply;
    }
}
