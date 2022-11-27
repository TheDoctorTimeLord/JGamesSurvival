package com.jgames.survival.viewmodel.core.model;

import ru.jengine.battlemodule.core.serviceclasses.Point;

public class PositionComponent extends GameObjectComponent {
    private Point position;

    public PositionComponent(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
