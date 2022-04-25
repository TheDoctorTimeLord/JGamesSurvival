package com.jgames.survival.model.game.logic.battle.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

/**
 * Класс, который хранит информацию о начальной позиции и направлении персонажа.
 */
public class StartPositionAction implements BattleAction {
    private final Point characterPosition;
    private final Direction characterDirection;
    private final int modelId;

    public StartPositionAction(int modelId, Point characterPosition, Direction characterDirection) {
        this.characterPosition = characterPosition;
        this.characterDirection = characterDirection;
        this.modelId = modelId;
    }

    public Direction getModelDirection() {
        return characterDirection;
    }

    public int getModelId() {
        return modelId;
    }

    public Point getModelPosition() {
        return characterPosition;
    }

    @Override
    public String toString() {
        return "StartPositionAction {" +
                "modelId=" + modelId +
                ", modelPosition=" + characterPosition +
                ", modelDirection=" + characterDirection +
                '}';
    }
}
