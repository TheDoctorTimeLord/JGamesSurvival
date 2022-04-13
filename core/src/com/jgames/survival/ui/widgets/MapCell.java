package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MapCell extends Stack {
    private final int row;
    private final int column;
    private final TextureRegion defaultTexture;

    public MapCell(int row, int column, TextureRegion defaultTexture, ClickOnMapCell callback) {
        super();
        this.row = row;
        this.column = column;
        this.defaultTexture = defaultTexture;

        MapHelper.makeCellEmpty(this);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.clicked(event, MapCell.this);
            }
        });
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

    public MapCell addPart(Actor cellPart) {
        add(cellPart);
        return this;
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
