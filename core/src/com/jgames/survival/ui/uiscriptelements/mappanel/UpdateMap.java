package com.jgames.survival.ui.uiscriptelements.mappanel;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;
import com.jgames.survival.presenter.filling.gamestate.modules.NameObjectResolvingModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.ModelDataPresenter;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import java.util.Collection;
import java.util.List;

/**
 * Скрипт для обновления поля боя.
 */
public class UpdateMap implements UIRunnableScript<EmptyScriptState> {
    private final GlobalMapWrapper<MapCell> globalMap;
    private final MapFillingPresenter mapFillingPresenter;
    private final ModelDataPresenter modelDataPresenter;
    private final NameObjectResolvingModule nameObjectResolvingModule;
    private final DrawingModulePresenter drawingModulePresenter;

    public UpdateMap(GlobalMapWrapper<MapCell> globalMap,
                     MapFillingPresenter mapFillingPresenter,
                     ModelDataPresenter modelDataPresenter,
                     NameObjectResolvingModule nameObjectResolvingModule,
                     DrawingModulePresenter drawingModulePresenter) {
        this.globalMap = globalMap;
        this.mapFillingPresenter = mapFillingPresenter;
        this.modelDataPresenter = modelDataPresenter;
        this.nameObjectResolvingModule = nameObjectResolvingModule;
        this.drawingModulePresenter = drawingModulePresenter;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        Collection<Point> points = mapFillingPresenter.getUpdatedCells();
        for (Point point : points) {
            List<Integer> objectIds = mapFillingPresenter.getIdsOnCell(point);
            List<ModelData> modelDataCollection = objectIds.stream().map(modelDataPresenter::getCurrentModelState).toList();
            List<ResolvingContext> resolvingContexts = nameObjectResolvingModule.resolveModelData(modelDataCollection);
            List<Actor> actors = resolvingContexts.stream().map(resolvingContext -> drawingModulePresenter.getActor(
                    resolvingContext.getObjectTypeName(),
                    resolvingContext.getDrawingContext())).toList();
            globalMap.getTableCell(point.getX(), point.getY()).update(actors);
        }
    }
}

