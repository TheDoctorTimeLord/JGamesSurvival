package com.jgames.survival.presenter.filling.gamestate.modules;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

public class ModelData {
    private final int id;
    private String typeName;
    private Point position;
    private Direction direction;
    private int hp = -1;

    public ModelData(int personId) {
        this.id = personId;
    }

    public ModelData(ModelData otherData) {
        this(otherData.id);
        setPosition(otherData.getPosition());
        setDirection(otherData.getDirection());
        setTypeName(otherData.getTypeName());
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public String getTypeName() {
        return typeName;
    }

    public ModelData setPosition(Point position) {
        this.position = position;
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public ModelData setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public int getHp() {
        return hp;
    }

    public void damage(int damage) {
        if (hp > 0) {
            hp -= damage;
            hp = Math.max(hp, 0);
        }
    }

    public void setMaxHp(int maxHp) {
        hp = hp < 0 ? maxHp : Math.min(hp, maxHp);
    }

    public boolean isKilled() {
        return hp == 0;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
