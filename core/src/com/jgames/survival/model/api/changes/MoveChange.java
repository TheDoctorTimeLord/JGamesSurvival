package com.jgames.survival.model.api.changes;

import ru.jengine.battlemodule.core.serviceclasses.Point;

public class MoveChange implements MotionChange {
    private final int personId;
    private final Point oldPosition;
    private final Point newPosition;

    public MoveChange(int personId, Point oldPosition, Point newPosition) {
        this.personId = personId;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    @Override
    public int getPersonId() {
        return personId;
    }

    public Point getOldPosition() {
        return oldPosition;
    }

    public Point getNewPosition() {
        return newPosition;
    }
}
