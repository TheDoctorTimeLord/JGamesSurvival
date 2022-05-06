package com.jgames.survival.presenter.filling.gamestate.model.objectcomponents;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.filling.gamestate.model.GameObjectComponent;

public class PositionComponent extends GameObjectComponent {
    private Point position;

    public PositionComponent(Point position) {
        this.position = position;
    }

    public synchronized Point getPosition() {
        return position;
    }

    public synchronized void setPosition(Point position) {
        this.position = position;
    }
}
