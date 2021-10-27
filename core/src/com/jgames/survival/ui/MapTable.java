package com.jgames.survival.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.ui.clickable.MapCell;
import com.jgames.survival.ui.clickable.MapCell.ClickOnMapCell;

public class MapTable extends Table {
    private volatile ScrollPane scrollPane;

    public MapTable(int width, int height, ClickOnMapCell cellCallback) {
        fillTable(width, height, cellCallback);
    }

    private void fillTable(int width, int height, ClickOnMapCell cellCallback) {
        Texture texture = new Texture("cell.png"); //TODO централизовать работу с assets
        TextureRegion region = new TextureRegion(texture);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                add(new MapCell(x, y, region, cellCallback));
            }
            row();
        }
    }

    public ScrollPane getScrollPaneWrapper() {
        if (scrollPane == null) {
            synchronized (this) {
                if (scrollPane == null) {
                    scrollPane = new ScrollPane(this);
                }
            }
        }
        return scrollPane;
    }
}
