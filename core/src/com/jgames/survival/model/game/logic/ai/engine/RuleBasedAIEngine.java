package com.jgames.survival.model.game.logic.ai.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jgames.survival.model.game.logic.ai.AIEngine;
import com.jgames.survival.model.game.logic.ai.FromCognitionBaseConvertable;
import com.jgames.survival.model.game.logic.ai.ToCognitionBaseConvertable;
import com.jgames.survival.model.game.logic.ai.ToCognitionConverter;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionDatabase;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;
import com.jgames.survival.model.game.logic.ai.cognition.impl.SimpleCognitionDatabase;
import com.jgames.survival.model.game.logic.ai.rules.Rule;
import com.jgames.survival.model.game.logic.ai.rules.RuleSet;
import com.jgames.survival.model.game.logic.ai.rules.impl.SerialRuleSet;

public class RuleBasedAIEngine implements AIEngine {
    private final List<ToCognitionConverter> toCognitionConverters = new ArrayList<>();
    private final Map<String, RuleSet> ruleSets = new HashMap<>();
    private final CognitionDatabase cognitionDatabase = new SimpleCognitionDatabase();

    @Override
    public void registerConverter(ToCognitionConverter converter) {
        toCognitionConverters.add(converter);
    }

    @Override
    public void registerRule(String ruleSetName, Rule rule) {
        ruleSets.computeIfAbsent(ruleSetName, c -> new SerialRuleSet()).addRule(rule);
    }

    @Override
    public void registerRuleSet(String ruleSetName, RuleSet ruleSet) {
        ruleSets.put(ruleSetName, ruleSet);
    }

    @Override
    public void registerCluster(String clusterName, CognitionCluster cluster) {
        cognitionDatabase.registerCluster(clusterName, cluster);
    }

    @Override
    public FromCognitionBaseConvertable select(List<ToCognitionBaseConvertable> cognitionBaseConvertibles) {
        return null;
    }
}
