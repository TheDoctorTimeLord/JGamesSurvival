package com.jgames.survival.presenter.filling.gamestate.model.objectcomponents;

import ru.jengine.battlemodule.core.serviceclasses.Direction;

import com.jgames.survival.presenter.core.model.GameObjectComponent;

public class DirectionComponent extends GameObjectComponent {
    private Direction direction;

    public DirectionComponent(Direction direction) {
        this.direction = direction;
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }
}
