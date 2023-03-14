package com.jgames.survival.model.game.logic.ai.cognition;

public interface CognitionDatabase {
    void registerCluster(String clusterName, CognitionCluster cluster);
    void registerClusterAlias(String clusterName, String alias);
    CognitionCluster getCluster(String clusterName);
}
