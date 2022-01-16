package com.jgames.survival.model.api.changes;

import ru.jengine.battlemodule.core.serviceclasses.Direction;

public class RotateChange implements MotionChange {
    private final int personId;
    private final Direction newDirection;

    public RotateChange(int personId, Direction newDirection) {
        this.personId = personId;
        this.newDirection = newDirection;
    }

    public Direction getNewDirection() {
        return newDirection;
    }

    @Override
    public int getPersonId() {
        return personId;
    }
}
