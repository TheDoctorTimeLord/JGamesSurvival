package com.jgames.survival.model.game.logic.ai.cognition.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import ru.jengine.utils.algorithms.BFS;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionRelation;

public class GraphCognitionCluster implements CognitionCluster {
    private final CognitionStorage cognitionStorage = new CognitionStorage();
    private final RelationStorage relationStorage = new RelationStorage();

    @Override
    public void addCognition(Cognition cognition) {
        cognitionStorage.addCognition(cognition);
    }

    @Override
    public void addRelation(Cognition from, Cognition to, CognitionRelation relation) {
        relationStorage.addRelation(relation, from, to);
    }

    @Override
    public void removeCognition(Cognition cognition) {
        cognitionStorage.removeCognition(cognition);
        relationStorage.removeRelationsIncidentCognition(cognition);
    }

    @Override
    public void removeRelation(Cognition from, Cognition to, CognitionRelation relation) {
        relationStorage.removeRelation(relation, from, to);
    }

    @Override
    public void removeRelation(CognitionRelation relation) {
        relationStorage.removeRelationBundle(relation);
    }

    @Override
    public Collection<Cognition> findCognition(String code) {
        return cognitionStorage.getCognition(code);
    }

    @Override
    public Collection<Cognition> findNearestNeighbours(Cognition cognition, CognitionRelation relation) {
        return relationStorage.getRelated(relation, cognition);
    }

    @Override
    public List<Cognition> findCognitionByRelation(List<Cognition> startCognition, CognitionRelation relation,
            Predicate<Cognition> cognitionFilter)
    {
        if (startCognition.isEmpty()) {
            return List.of();
        }

        Cognition firstCognition = startCognition.get(0);
        List<Cognition> foundedCognition = new ArrayList<>();

        BFS.runAlgorithm(
                firstCognition,
                cognition -> {
                    Stream<Cognition> relatedCognition = relationStorage.getRelated(relation, cognition).stream();
                    Stream<Cognition> cognitionForFiltering = firstCognition != cognition
                            ? relatedCognition
                            : Stream.concat(startCognition.stream(), relatedCognition);
                    return cognitionForFiltering.filter(cognitionFilter).toList();
                },
                null,
                foundedCognition::add,
                null
        );

        return foundedCognition;
    }

    @Override
    public List<Cognition> findMostDistanceCognition(Cognition startCognition, CognitionRelation relation) {
        DistanceMeter distanceMeter = new DistanceMeter(startCognition);

        BFS.runAlgorithm(
                startCognition,
                cognition -> relationStorage.getRelated(relation, cognition),
                null,
                null,
                distanceMeter
        );

        return distanceMeter.getCognitionOnMaxDistance();
    }

    @Override
    public Collection<Cognition> findCognitionByRelationChain(Cognition startCognition,
            List<CognitionRelation> relationChain)
    {
        if (relationChain.isEmpty()) {
            return List.of(startCognition);
        }

        Queue<CognitionRelation> relationQueue = new ArrayDeque<>(relationChain);
        Set<Cognition> currentRelatedCognition = Set.of(startCognition);

        while (!currentRelatedCognition.isEmpty() && !relationQueue.isEmpty()) {
            CognitionRelation relation = relationQueue.poll();
            Set<Cognition> relatedCondition = new HashSet<>();

            for (Cognition cognition : currentRelatedCognition) {
                relatedCondition.addAll(relationStorage.getRelated(relation, cognition));
            }

            currentRelatedCognition = relatedCondition;
        }

        return currentRelatedCognition;
    }

    private static class DistanceMeter implements BiConsumer<Cognition, Cognition> {
        private final Map<Cognition, Integer> distanceByCognition = new HashMap<>();
        private final List<Cognition> cognitionOnMaxDistance = new ArrayList<>();
        private Integer maxDistance = 0;

        public DistanceMeter(Cognition startPoint) {
            distanceByCognition.put(startPoint, maxDistance);
            addCognitionOnMaxDistance(startPoint, maxDistance);
        }

        @Override
        public void accept(Cognition cognitionFrom, Cognition cognitionTo) {
            Integer distance = distanceByCognition.get(cognitionFrom);
            if (distance == null) {
                throw new IllegalStateException("Cognition did not accepted already [%s]".formatted(cognitionFrom));
            }
            Integer newDistance = distance + 1;
            distanceByCognition.put(cognitionTo, newDistance);
            addCognitionOnMaxDistance(cognitionTo, newDistance);
        }

        public List<Cognition> getCognitionOnMaxDistance() {
            return cognitionOnMaxDistance;
        }

        private void addCognitionOnMaxDistance(Cognition cognition, Integer distance) {
            updateMaxDistance(distance);
            if (distance.compareTo(maxDistance) == 0) {
                cognitionOnMaxDistance.add(cognition);
            }
        }

        private void updateMaxDistance(Integer currentDistance) {
            if (currentDistance.compareTo(maxDistance) > 0) {
                maxDistance = currentDistance;
                cognitionOnMaxDistance.clear();
            }
        }
    }
}
