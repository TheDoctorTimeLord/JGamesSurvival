package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;

public class FighterType extends AttributeBasedType {
    public FighterType(String objectTypeName, AttributesContainer attributesContainer) {
        super(objectTypeName, attributesContainer);
    }

    @Override
    public Fighter createBattleModelByType(int modelId) {
        return new Fighter(modelId, this, getAttributes().clone());
    }
}
