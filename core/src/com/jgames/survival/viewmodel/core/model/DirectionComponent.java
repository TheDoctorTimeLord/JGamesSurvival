package com.jgames.survival.viewmodel.core.model;

import ru.jengine.battlemodule.core.serviceclasses.Direction;

public class DirectionComponent extends GameObjectComponent {
    private Direction direction;

    public DirectionComponent(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
