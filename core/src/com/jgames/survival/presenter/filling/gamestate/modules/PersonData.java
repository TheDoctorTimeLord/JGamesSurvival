package com.jgames.survival.presenter.filling.gamestate.modules;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

public class PersonData {
    private final int id;
    private int hp;
    private Point position;
    private Direction direction;

    public PersonData(int personId, int startedHp, Point startedPosition, Direction direction) {
        this.id = personId;
        this.hp = startedHp;
        this.position = startedPosition;
        this.direction = direction;
    }

    public PersonData(PersonData otherData) {
        this(otherData.id, otherData.hp, otherData.position, otherData.direction);
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHp() {
        return hp;
    }

    public void damage(int damage) {
        this.hp -= damage;
    }

    public boolean isKilled() {
        return hp <= 0;
    }
}
