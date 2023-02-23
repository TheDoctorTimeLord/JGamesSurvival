package com.jgames.survival.model.game.logic.ai.cognition.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jgames.survival.model.game.logic.ai.cognition.Cognition;
import com.jgames.survival.model.game.logic.ai.cognition.CognitionRelation;
import com.jgames.survival.utils.Multimap;

public class RelationStorage {
    private final Map<CognitionRelation, RelationBundle> relations = new HashMap<>();

    public void addRelation(CognitionRelation relationType, Cognition from, Cognition to) {
        relations.computeIfAbsent(relationType, t -> new RelationBundle())
                .addRelation(from, to);
    }

    public void removeRelationBundle(CognitionRelation relation) {
        relations.remove(relation);
    }

    public void removeRelation(CognitionRelation relationType, Cognition from, Cognition to) {
        RelationBundle relationBundle = relations.get(relationType);
        if (relationBundle != null) {
            relationBundle.removeRelation(from, to);
        }
    }

    public void removeRelationsIncidentCognition(Cognition incidentTo) {
        for (RelationBundle bundle : relations.values()) {
            bundle.removeIncidentRelations(incidentTo);
        }
    }

    public Collection<Cognition> getRelated(CognitionRelation relationType, Cognition from) {
        RelationBundle relationBundle = relations.get(relationType);
        return relationBundle == null
                ? Collections.emptySet()
                : relationBundle.getRelated(from);
    }

    private static class RelationBundle {
        private final Multimap<Cognition, Cognition> reletedKnowledges = new Multimap<>();

        public void addRelation(Cognition from, Cognition to) {
            reletedKnowledges.add(from, to);
        }

        public void removeRelation(Cognition from, Cognition to) {
            reletedKnowledges.removeItem(from, to);
        }

        public void removeIncidentRelations(Cognition incidentTo) {
            reletedKnowledges.removeKey(incidentTo);
            reletedKnowledges.removeValues(incidentTo);
        }

        public Collection<Cognition> getRelated(Cognition from) {
            return reletedKnowledges.asMap().getOrDefault(from, Collections.emptySet());
        }
    }
}
