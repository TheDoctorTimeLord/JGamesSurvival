package com.jgames.survival.model.game.logic.ai.cognition;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface CognitionCluster {
    void addCognition(Cognition cognition);
    void addRelation(Cognition from, Cognition to, CognitionRelation relation);

    void removeCognition(Cognition cognition);
    void removeRelation(Cognition from, Cognition to, CognitionRelation relation);
    void removeRelation(CognitionRelation relation);

    Collection<Cognition> findCognition(String code);
    Collection<Cognition> findNearestNeighbours(Cognition cognition, CognitionRelation relation);

    List<Cognition> findCognitionByRelation(List<Cognition> startCognition, CognitionRelation relation,
            Predicate<Cognition> cognitionFilter);
    List<Cognition> findMostDistanceCognition(Cognition startCognition, CognitionRelation relation);
    Collection<Cognition> findCognitionByRelationChain(Cognition startCognition, List<CognitionRelation> relationChain);
}
