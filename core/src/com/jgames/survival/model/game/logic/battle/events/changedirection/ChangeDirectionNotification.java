package com.jgames.survival.model.game.logic.battle.events.changedirection;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.serviceclasses.Direction;

public class ChangeDirectionNotification implements BattleAction {
    private final int modelId;
    private final Direction newDirection;

    public ChangeDirectionNotification(ChangeDirectionEvent event) {
        this.modelId = event.getModelId();
        this.newDirection = event.getNewDirection();
    }

    public int getModelId() {
        return modelId;
    }

    public Direction getNewDirection() {
        return newDirection;
    }

    @Override
    public String toString() {
        return "ChangeDirectionNotification{" +
                "modelId=" + modelId +
                ", newDirection=" + newDirection +
                '}';
    }
}
