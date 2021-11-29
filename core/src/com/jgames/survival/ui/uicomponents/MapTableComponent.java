package com.jgames.survival.ui.uicomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.ui.UIComponent;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapCell.ClickOnMapCell;

public class MapTableComponent implements UIComponent {
    private final int width;
    private final int height;
    private final ClickOnMapCell cellCallback;
    private GlobalMapWrapper<MapCell> globalMap;

    private ScrollPane mapTableScrollableWrapper;

    public MapTableComponent(int width, int height, ClickOnMapCell cellCallback) {
        this.width = width;
        this.height = height;
        this.cellCallback = cellCallback;
    }

    @Override
    public void prepareComponent(UIElements uiElements) {
        Texture texture = new Texture("cell.png"); //TODO централизовать работу с assets
        TextureRegion region = new TextureRegion(texture);

        globalMap = createGlobalMap(region);
        createGlobalMapScrollableWrapper(globalMap);
    }

    private void createGlobalMapScrollableWrapper(GlobalMapWrapper<MapCell> mapTable) {
        mapTableScrollableWrapper = new ScrollPane(mapTable.getWrapped());
        mapTableScrollableWrapper.setFillParent(true);
    }

    private GlobalMapWrapper<MapCell> createGlobalMap(TextureRegion region) {
        Table mapTable = new Table();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                mapTable.add(new MapCell(x, y, region, cellCallback));
            }
            mapTable.row();
        }
        return new GlobalMapWrapper<>(mapTable);
    }

    @Override
    public Actor getFrontWidget() {
        return mapTableScrollableWrapper;
    }

    @Override
    public Actor getActionableComponent() {
        return globalMap;
    }
}
