package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;

public class StaticModelType extends AttributeBasedType {

    public StaticModelType(String name, AttributesContainer attributesContainer) {
        super(name, attributesContainer);
    }

    @Override
    public StaticModel createBattleModelByType(int modelId) {
        return new StaticModel(modelId, this, getAttributes().clone());
    }
}
