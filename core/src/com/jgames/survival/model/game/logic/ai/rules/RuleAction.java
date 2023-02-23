package com.jgames.survival.model.game.logic.ai.rules;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionDataBase;

public interface RuleAction {
    void applyAction(RuleCheckResult checkResult, CognitionDataBase cognitionDataBase);
}
