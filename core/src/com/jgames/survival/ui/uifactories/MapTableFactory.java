package com.jgames.survival.ui.uifactories;

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
import com.jgames.survival.presenter.filling.gamestate.mutators.MapFillingMutator;
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
        PresentingGameState gameState = uiElements.getPresentingGameState();
        MapFillingPresenter mapFillingPresenter = gameState.getModulePresenter(MapFillingModule.NAME);
        GameObjectsPresenter gameObjectsPresenter = gameState.getModulePresenter(GameObjectsModule.NAME);
        NameObjectResolvingPresenter nameObjectResolvingPresenter = gameState.getModulePresenter(NameObjectResolvingModule.NAME);
        DrawingModulePresenter drawingModulePresenter = gameState.getModulePresenter(DrawingModule.NAME);

        MapFillingMutator mapFillingMutator = gameState.getModuleMutator(MapFillingMutator.class);

        globalMap = createGlobalMap(mapFillingMutator);

        UIScriptMachine scriptMachine = uiElements.getScriptMachine();
        scriptMachine.registerScript(new CyclicUIScript<>(GLOBAL_MAP_UPDATE_SCRIPT_NAME, new EmptyScriptState(),
                new WaitUpdateMapAction(),
                new UpdateMap(globalMap, mapFillingPresenter, gameObjectsPresenter, nameObjectResolvingPresenter, drawingModulePresenter)
        ));

        createGlobalMapScrollableWrapper(globalMap);

        scriptMachine.deleteScript(GLOBAL_MAP_UPDATE_SCRIPT_NAME);
    }

    private void createGlobalMapScrollableWrapper(GlobalMapWrapper<MapCell> mapTable) {
        mapTableScrollableWrapper = new ScrollPane(mapTable.getWrapped());
        mapTableScrollableWrapper.setFillParent(true);
    }

    private GlobalMapWrapper<MapCell> createGlobalMap(MapFillingMutator mapFillingMutator) {
        Table mapTable = new Table();

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                MapCell mapCell = new MapCell(x, y, cellCallback);
                mapTable.add(mapCell);
                mapFillingMutator.addMapCellItem(mapCell.getCoordinateAsPoint());
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
