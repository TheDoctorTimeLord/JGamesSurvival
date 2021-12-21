package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MapCell extends ImageButton { //TODO перейти на контейнер объектов
    private final int row;
    private final int column;

    private final Drawable defaultTextureUp;
    private final Drawable defaultTextureDown;

    public MapCell(int row, int column, TextureRegion sprite, ClickOnMapCell callback) {
        super(createMapCellStyle(sprite)); //TODO перейти к Skin
        this.row = row;
        this.column = column;

        defaultTextureUp = new TextureRegionDrawable(sprite);
        defaultTextureDown = new TextureRegionDrawable(sprite).tint(new Color(0.9f, 0.9f, 0.9f, 1f));

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

    public void setTexture(TextureRegion sprite) { //TODO придумать что-то получше, чем сетить текстуру
        getStyle().up = new TextureRegionDrawable(sprite);
        getStyle().down = new TextureRegionDrawable(sprite).tint(new Color(0.9f, 0.9f, 0.9f, 1f));
    }

    public void resetTexture() {
        getStyle().up = defaultTextureUp;
        getStyle().down = defaultTextureDown;
    }

    @FunctionalInterface
    public interface ClickOnMapCell {
        void clicked(InputEvent event, MapCell mapCell);
    }
}
