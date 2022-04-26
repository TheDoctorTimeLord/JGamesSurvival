package com.jgames.survival.ui.widgets;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MapCell extends Stack {
    private final int row;
    private final int column;

    public MapCell(int row, int column, ClickOnMapCell callback) {
        super();
        setTouchable(Touchable.enabled);
        this.row = row;
        this.column = column;

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
        return column;
    }

    public int getRow() {
        return row;
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
