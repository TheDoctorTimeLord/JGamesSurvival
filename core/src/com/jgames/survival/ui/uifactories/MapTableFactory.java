package com.jgames.survival.ui.uifactories;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonData;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonDataModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.PersonDataPresenter;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapCell.ClickOnMapCell;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.utils.assets.TextureStorage;

public class MapTableFactory implements UIFactory {
    private final int width;
    private final int height;
    private final ClickOnMapCell cellCallback;
    private GlobalMapWrapper<MapCell> globalMap;

    private ScrollPane mapTableScrollableWrapper;

    public MapTableFactory(int width, int height, ClickOnMapCell cellCallback) {
        this.width = width;
        this.height = height;
        this.cellCallback = cellCallback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureStorage storage = uiElements.getTextureStorage();
        TextureRegion region = storage.createSprite(Constants.COMMON);

        globalMap = createGlobalMap(region);

        PersonDataPresenter presenter = uiElements.getPresentingGameState()
                .getModulePresenter(PersonDataModule.NAME);
        fillGlobalMap(presenter, new TextureRegion[] {
                storage.createSprite(Constants.PERSON_UP),
                storage.createSprite(Constants.PERSON_RIGHT),
                storage.createSprite(Constants.PERSON_DOWN),
                storage.createSprite(Constants.PERSON_LEFT)
        });

        createGlobalMapScrollableWrapper(globalMap);
    }

    private void fillGlobalMap(PersonDataPresenter personDataPresenter, TextureRegion[] directedPersonTextures) {
        for (PersonData personData : personDataPresenter.getDataForAllPersons()) {
            Point position = personData.getPosition();
            globalMap.getTableCell(position.getY(), position.getX())
                    .setTexture(directedPersonTextures[personData.getDirection().ordinal()]);
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
