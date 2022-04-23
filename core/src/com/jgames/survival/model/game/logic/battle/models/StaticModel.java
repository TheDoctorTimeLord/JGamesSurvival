package com.jgames.survival.model.game.logic.battle.models;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.models.BattleModelType;
import ru.jengine.battlemodule.core.models.HasPosition;
import ru.jengine.battlemodule.core.serviceclasses.Point;

/**
 * Класс, описывающий статический объект в бою. Статический объект характеризуется тем, что он находится на некоторой позиции поля боя
 */
public class StaticModel extends BattleModel implements HasPosition {
    private Point position;

    public StaticModel(int id, BattleModelType type, AttributesContainer attributes) {
        super(id, type, attributes);
    }

    @Override
    public boolean hasPosition() {
        return this.position != null;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }
}
