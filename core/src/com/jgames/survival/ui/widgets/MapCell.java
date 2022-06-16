package com.jgames.survival.ui.widgets;

import java.util.List;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MapCell extends Stack {
    private final Point tableCoordinate;
    private final Point logicCoordinate;

    public MapCell(Point logicOffset, int row, int column, ClickOnMapCell callback) {
        super();
        setTouchable(Touchable.enabled);

        this.tableCoordinate = PointPool.obtain(row, column);
        this.logicCoordinate = tableCoordinate.add(logicOffset);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.clicked(event, MapCell.this);
            }
        });
    }

    public void update(List<Actor> actors) {
        clearCell();
        actors.forEach(this::add);
    }

    public int getColumn() {
        return tableCoordinate.getY();
    }

    public int getRow() {
        return tableCoordinate.getX();
    }

    public Point getCoordinateAsPoint() {
        return logicCoordinate;
    }

    public MapCell clearCell() {
        clearChildren();
        return this;
    }

    @FunctionalInterface
    public interface ClickOnMapCell {
        void clicked(InputEvent event, MapCell mapCell);
    }
}
