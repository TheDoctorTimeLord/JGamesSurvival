package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.models.BattleModelType;

public abstract class AttributeBasedType implements BattleModelType {
    private final String objectTypeName;
    private final AttributesContainer attributesContainer;

    public AttributeBasedType(String objectTypeName, AttributesContainer attributesContainer) {
        this.objectTypeName = objectTypeName;
        this.attributesContainer = attributesContainer;
    }

    @Override
    public AttributesContainer getAttributes() {
        return attributesContainer;
    }

    @Override
    public String getObjectTypeName() {
        return objectTypeName;
    }
}
