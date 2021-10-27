package com.jgames.survival.ui.clickable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MapCell extends ImageButton {
    private final int row;
    private final int column;

    public MapCell(int row, int column, TextureRegion sprite, ClickOnMapCell callback) {
        super(createMapCellStyle(sprite)); //TODO перейти к Skin
        this.row = row;
        this.column = column;

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.clicked(event, MapCell.this);
            }
        });
    }

    private static ImageButtonStyle createMapCellStyle(TextureRegion sprite) {
        ImageButtonStyle style = new ImageButtonStyle();
        style.up = new TextureRegionDrawable(sprite);
        style.down = new TextureRegionDrawable(sprite).tint(new Color(0.9f, 0.9f, 0.9f, 1f));
        return style;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @FunctionalInterface
    public interface ClickOnMapCell {
        void clicked(InputEvent event, MapCell mapCell);
    }
}
