package com.jgames.survival.model.game.logic.battle.events.move;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.standardfilling.movement.MoveEvent;

public class MoveBattleAction implements BattleAction {
    private final int modelId;
    private final Point oldPosition;
    private final Point newPosition;

    public MoveBattleAction(MoveEvent event) {
        this.modelId = event.getModelId();
        this.oldPosition = event.getOldPosition();
        this.newPosition = event.getNewPosition();
    }

    public int getModelId() {
        return modelId;
    }

    public Point getNewPosition() {
        return newPosition;
    }

    public Point getOldPosition() {
        return oldPosition;
    }

    @Override
    public String toString() {
        return "MoveBattleAction{" +
                "modelId=" + modelId +
                ", oldPosition=" + oldPosition +
                ", newPosition=" + newPosition +
                '}';
    }
}
