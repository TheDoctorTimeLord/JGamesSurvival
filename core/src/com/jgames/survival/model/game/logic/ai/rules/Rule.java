package com.jgames.survival.model.game.logic.ai.rules;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionDataBase;

public interface Rule {
    RuleCheckResult checkRule(CognitionDataBase cognitionDataBase);
    void applyRule(RuleCheckResult checkResult, CognitionDataBase cognitionDataBase);
}
