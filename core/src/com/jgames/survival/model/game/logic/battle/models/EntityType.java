package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModelType;

public class EntityType extends DynamicModelType {
    public EntityType(String objectTypeName, AttributesContainer attributesContainer) {
        super(objectTypeName, attributesContainer);
    }

    @Override
    public Entity createBattleModelByType(int modelId) {
        return new Entity(modelId, this, getAttributes().clone());
    }
}
