package com.jgames.survival.model.game.logic.battle.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

public class ObjectTypeAction implements BattleAction {
    private final int modelId;
    private final String modelTypeName;

    public ObjectTypeAction(int modelId, String modelTypeName) {
        this.modelId = modelId;
        this.modelTypeName = modelTypeName;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelTypeName() {
        return modelTypeName;
    }

    @Override
    public String toString() {
        return "ObjectTypeAction {" +
                "modelId=" + modelId +
                ", modelTypeName='" + modelTypeName + '\'' +
                '}';
    }
}
