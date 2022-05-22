package com.jgames.survival.model.game.logic.battle.events.deadmodel;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.events.BattleEvent;

public class DeadDynamicModelEvent extends BattleEvent implements BattleAction {
    private final int modelId;

    public DeadDynamicModelEvent(int modelId) {
        this.modelId = modelId;
    }

    public int getModelId() {
        return modelId;
    }

    @Override
    public String toString() {
        return "DeadDynamicModelEvent{" +
                "modelId=" + modelId +
                '}';
    }
}
