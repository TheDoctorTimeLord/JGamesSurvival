package com.jgames.survival.model.game.logic.battle.events.changedirection;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.events.BattleEvent;
import ru.jengine.battlemodule.core.serviceclasses.Direction;

public class ChangeDirectionEvent extends BattleEvent implements BattleAction {
    private final int modelId;
    private final Direction newDirection;

    public ChangeDirectionEvent(int modelId, Direction newDirection) {
        this.modelId = modelId;
        this.newDirection = newDirection;
    }

    public int getModelId() {
        return modelId;
    }

    public Direction getNewDirection() {
        return newDirection;
    }

    @Override
    public String toString() {
        return "ChangeDirectionEvent{" +
                "modelId=" + modelId +
                ", newDirection=" + newDirection +
                '}';
    }
}
