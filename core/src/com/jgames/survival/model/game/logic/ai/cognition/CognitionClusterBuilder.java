package com.jgames.survival.model.game.logic.ai.cognition;

import com.jgames.survival.model.game.logic.ai.cognition.impl.GraphCognitionCluster;

public class CognitionClusterBuilder {
    private final CognitionCluster cluster;

    public static CognitionClusterBuilder builder() {
        return new CognitionClusterBuilder(new GraphCognitionCluster());
    }

    public static CognitionClusterBuilder builder(CognitionCluster cluster) {
        return new CognitionClusterBuilder(cluster);
    }

    private CognitionClusterBuilder(CognitionCluster cluster) {
        this.cluster = cluster;
    }

    public CognitionClusterBuilder addCognition(Cognition cognition) {
        cluster.addCognition(cognition);
        return this;
    }

    public CognitionClusterBuilder addCognitionRelation(CognitionRelation relation, Cognition fromCognition,
            Cognition... toCognition)
    {
        for (Cognition to : toCognition) {
            cluster.addRelation(fromCognition, to, relation);
        }
        return this;
    }

    public CognitionCluster build() {
        return cluster;
    }
}
