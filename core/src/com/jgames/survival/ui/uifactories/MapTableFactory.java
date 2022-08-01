package com.jgames.survival.ui.uifactories;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.utils.Pair;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIScriptMachine;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.GameObjectsModule;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.NameObjectResolvingModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.GameObjectsPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.NameObjectResolvingPresenter;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.uiscriptelements.mappanel.UpdateMap;
import com.jgames.survival.ui.uiscriptelements.mappanel.WaitUpdateMapAction;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapCell.ClickOnMapCell;

public class MapTableFactory implements UIFactory {
    private static final String GLOBAL_MAP_UPDATE_SCRIPT_NAME = "globalMapUpdate";

    private final ClickOnMapCell cellCallback;
    private GlobalMapWrapper<MapCell> globalMap;

    private ScrollPane mapTableScrollableWrapper;

    public MapTableFactory(ClickOnMapCell cellCallback) {
        this.cellCallback = cellCallback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        PresentingGameState gameState = uiElements.getPresentingGameState();
        MapFillingPresenter mapFillingPresenter = gameState.getModulePresenter(MapFillingModule.NAME);
        GameObjectsPresenter gameObjectsPresenter = gameState.getModulePresenter(GameObjectsModule.NAME);
        NameObjectResolvingPresenter nameObjectResolvingPresenter = gameState.getModulePresenter(NameObjectResolvingModule.NAME);
        DrawingModulePresenter drawingModulePresenter = gameState.getModulePresenter(DrawingModule.NAME);

        globalMap = createGlobalMap(mapFillingPresenter);

        UIScriptMachine scriptMachine = uiElements.getScriptMachine();
        scriptMachine.registerScript(new CyclicUIScript<>(GLOBAL_MAP_UPDATE_SCRIPT_NAME, new EmptyScriptState(),
                new WaitUpdateMapAction(),
                new UpdateMap(globalMap, mapFillingPresenter, gameObjectsPresenter, nameObjectResolvingPresenter, drawingModulePresenter)
        ));

        createGlobalMapScrollableWrapper(globalMap);
    }

    private void createGlobalMapScrollableWrapper(GlobalMapWrapper<MapCell> mapTable) {
        mapTableScrollableWrapper = new ScrollPane(mapTable.getWrapped());
        mapTableScrollableWrapper.setFillParent(true);
    }

    private GlobalMapWrapper<MapCell> createGlobalMap(MapFillingPresenter mapFillingPresenter) {
        Pair<Point, Point> rectangle = mapFillingPresenter.getBattlefieldRectangleCoordinate();
        Point offset = rectangle.getKey();
        int width = rectangle.getValue().getX() - offset.getX() + 1;
        int height = rectangle.getValue().getY() - offset.getY() + 1;

        Table mapTable = new Table();

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                MapCell mapCell = new MapCell(offset, x, y, cellCallback);
                mapTable.add(mapCell);
            }
            mapTable.row();
        }

        mapTable.padTop(100)
                .padBottom(100)
                .padLeft(100)
                .padRight(150);

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
