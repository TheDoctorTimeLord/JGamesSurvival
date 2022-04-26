package com.jgames.survival.ui.uifactories;

import ru.jengine.battlemodule.core.serviceclasses.PointPool;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIScriptMachine;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelDataModule;
import com.jgames.survival.presenter.filling.gamestate.modules.NameObjectResolvingModule;
import com.jgames.survival.presenter.filling.gamestate.mutators.ModelDataMutator;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.ModelDataPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.NameObjectResolvingPresenter;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.uiscriptelements.mappanel.UpdateMap;
import com.jgames.survival.ui.uiscriptelements.mappanel.WaitUpdateMapAction;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapCell.ClickOnMapCell;

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
        PresentingGameState gameState = uiElements.getPresentingGameState();
        MapFillingPresenter mapFillingPresenter = gameState.getModulePresenter(MapFillingModule.NAME);
        ModelDataPresenter modelDataPresenter = gameState.getModulePresenter(ModelDataModule.NAME);
        NameObjectResolvingPresenter nameObjectResolvingPresenter = gameState.getModulePresenter(NameObjectResolvingModule.NAME);
        DrawingModulePresenter drawingModulePresenter = gameState.getModulePresenter(DrawingModule.NAME);

        ModelDataMutator modelDataMutator = gameState.getModuleMutator(ModelDataMutator.class);

        globalMap = createGlobalMap(modelDataMutator);

        UIScriptMachine scriptMachine = uiElements.getScriptMachine();
        scriptMachine.registerScript(new CyclicUIScript<>("globalMapUpdater", new EmptyScriptState(),
                new WaitUpdateMapAction(),
                new UpdateMap(globalMap, mapFillingPresenter, modelDataPresenter, nameObjectResolvingPresenter, drawingModulePresenter)
        ));

        createGlobalMapScrollableWrapper(globalMap);
    }

    private void createGlobalMapScrollableWrapper(GlobalMapWrapper<MapCell> mapTable) {
        mapTableScrollableWrapper = new ScrollPane(mapTable.getWrapped());
        mapTableScrollableWrapper.setFillParent(true);
    }

    private GlobalMapWrapper<MapCell> createGlobalMap(ModelDataMutator modelDataMutator) {
        Table mapTable = new Table();

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                mapTable.add(new MapCell(x, y, cellCallback));
                modelDataMutator.markCellAsUpdated(PointPool.obtain(x, y));
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
