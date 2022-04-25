package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.List;

public class MapCell extends Stack {
    private final int row;
    private final int column;
    private final TextureRegion defaultTexture;

    public MapCell(int row, int column, TextureRegion defaultTexture, ClickOnMapCell callback) {
        super();
        setTouchable(Touchable.enabled);
        this.row = row;
        this.column = column;
        this.defaultTexture = defaultTexture;

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

    public TextureRegion getDefaultTexture() {
        return defaultTexture;
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
