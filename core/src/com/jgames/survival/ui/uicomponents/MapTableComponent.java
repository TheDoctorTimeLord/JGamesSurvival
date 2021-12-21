package com.jgames.survival.ui.uicomponents;

import java.util.stream.Stream;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.test.annotation.battle.battleactions.initializeBattle.StartPositionAction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.model.api.changes.StartPositionData;
import com.jgames.survival.ui.UIComponent;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapCell.ClickOnMapCell;

public class MapTableComponent implements UIComponent {
    private final int width;
    private final int height;
    private final ClickOnMapCell cellCallback;
    private final TextureRegion[] directedPersonTextures;
    private GlobalMapWrapper<MapCell> globalMap;

    private ScrollPane mapTableScrollableWrapper;

    public MapTableComponent(int width, int height, ClickOnMapCell cellCallback, Texture... directedPerson) {
        this.width = width;
        this.height = height;
        this.cellCallback = cellCallback;
        this.directedPersonTextures = Stream.of(directedPerson)
                .map(TextureRegion::new)
                .toArray(TextureRegion[]::new);
    }

    @Override
    public void prepareComponent(UIElements uiElements) {
        Texture texture = new Texture("cell.png"); //TODO централизовать работу с assets
        TextureRegion region = new TextureRegion(texture);

        globalMap = createGlobalMap(region);

        StartPositionData startPositionData = uiElements.getPresentingGameState().getStartPositions();
        fillGlobalMap(startPositionData);

        createGlobalMapScrollableWrapper(globalMap);
    }

    private void fillGlobalMap(StartPositionData startPositionData) {
        for (StartPositionAction startPosition : startPositionData.getStartPositions()) {
            Point position = startPosition.getCharacterPosition();
            globalMap.getTableCell(position.getY(), position.getX())
                    .setTexture(directedPersonTextures[startPosition.getCharacterDirection().ordinal()]);
        }
    }

    private void createGlobalMapScrollableWrapper(GlobalMapWrapper<MapCell> mapTable) {
        mapTableScrollableWrapper = new ScrollPane(mapTable.getWrapped());
        mapTableScrollableWrapper.setFillParent(true);
    }

    private GlobalMapWrapper<MapCell> createGlobalMap(TextureRegion region) {
        Table mapTable = new Table();

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
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
