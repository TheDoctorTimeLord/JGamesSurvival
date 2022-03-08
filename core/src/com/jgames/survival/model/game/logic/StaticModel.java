package com.jgames.survival.model.game.logic;

import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Point;

/**
 * Класс, описывающий статический объект в бою. Статический объект характеризуется тем, что он находится на некоторой позиции поля боя
 */
public class StaticModel extends BattleModel {
    private Point position;

    public StaticModel(int id) {
        super(id);
    }

    public boolean hasPosition() {
        return this.position != null;
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
