package com.jgames.survival.model.game.logic.ai;

import java.util.List;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;
import com.jgames.survival.model.game.logic.ai.rules.Rule;
import com.jgames.survival.model.game.logic.ai.rules.RuleSet;

public interface AIEngine {
    void registerConverter(ToCognitionConverter converter);
    void registerRule(String ruleSetName, Rule rule);
    void registerRuleSet(String ruleSetName, RuleSet ruleSet);
    void registerCluster(String clusterName, CognitionCluster cluster);
    FromCognitionBaseConvertable select(List<ToCognitionBaseConvertable> cognitionBaseConvertibles);
}
