package com.jgames.survival.model.game.logic.ai.rules;

import java.util.List;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionDataBase;

public interface RuleCondition {
    List<Cognition> applyCondition(CognitionDataBase cognitionDataBase);
}
