package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.models.BattleModelType;

public class StaticModelType implements BattleModelType {
    private final String name;
    private final AttributesContainer attributesContainer;

    public StaticModelType(String name, AttributesContainer attributesContainer) {
        this.name = name;
        this.attributesContainer = attributesContainer;
    }

    @Override
    public AttributesContainer getAttributes() {
        return attributesContainer;
    }

    @Override
    public String getObjectTypeName() {
        return name;
    }

    @Override
    public StaticModel createBattleModelByType(int modelId) {
        return new StaticModel(modelId, this, attributesContainer.clone());
    }
}
