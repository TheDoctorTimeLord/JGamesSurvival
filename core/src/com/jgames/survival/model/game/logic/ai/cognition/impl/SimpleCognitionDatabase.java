package com.jgames.survival.model.game.logic.ai.cognition.impl;

import java.util.HashMap;
import java.util.Map;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionDatabase;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;

public class SimpleCognitionDatabase implements CognitionDatabase {
    private final Map<String, CognitionCluster> clusters = new HashMap<>();
    private final Map<String, String> clusterAliases = new HashMap<>();

    @Override
    public void registerCluster(String clusterName, CognitionCluster cluster) {
        clusters.put(clusterName, cluster);
    }

    @Override
    public void registerClusterAlias(String clusterName, String alias) {
        clusterAliases.put(alias, clusterName);
    }

    @Override
    public CognitionCluster getCluster(String clusterName) {
        String aliasedDatabaseName = clusterAliases.getOrDefault(clusterName, clusterName);
        CognitionCluster database = clusters.get(aliasedDatabaseName);

        if (database == null) {
            throw new IllegalArgumentException("Illegal database name [%s]".formatted(clusterName));
        }

        return database;
    }
}
