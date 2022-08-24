package com.jgames.survival.ui.uiscriptelements.mappanel;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.GameObjectsPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.NameObjectResolvingPresenter;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;

/**
 * Скрипт для обновления поля боя.
 */
public class UpdateMap implements UIRunnableScript<EmptyScriptState> {
    private final GlobalMapWrapper<MapCell> globalMap;
    private final MapFillingPresenter mapFillingPresenter;
    private final GameObjectsPresenter gameObjectsPresenter;
    private final NameObjectResolvingPresenter nameObjectResolvingModule;
    private final DrawingModulePresenter drawingModulePresenter;

    public UpdateMap(GlobalMapWrapper<MapCell> globalMap,
            MapFillingPresenter mapFillingPresenter,
            GameObjectsPresenter gameObjectsPresenter,
            NameObjectResolvingPresenter nameObjectResolvingModule,
            DrawingModulePresenter drawingModulePresenter) {
        this.globalMap = globalMap;
        this.mapFillingPresenter = mapFillingPresenter;
        this.gameObjectsPresenter = gameObjectsPresenter;
        this.nameObjectResolvingModule = nameObjectResolvingModule;
        this.drawingModulePresenter = drawingModulePresenter;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        Collection<Point> points = mapFillingPresenter.getUpdatedCells();
        for (Point point : points) {
            List<Integer> objectIds = mapFillingPresenter.getIdsOnCell(point);
            List<GameObject> gameObjectCollection = objectIds.stream().map(gameObjectsPresenter::getCurrentObjectState).toList();
            List<ResolvingContext> resolvingContexts = nameObjectResolvingModule.resolveModelData(gameObjectCollection);
            List<Actor> actors = resolvingContexts.stream()
                    .flatMap(this::resolveByContext)
                    .toList();
            globalMap.getTableCell(point.getX(), point.getY()).update(actors);
        }
    }

    private Stream<Actor> resolveByContext(ResolvingContext resolvingContext) {
        GameObject gameObject = resolvingContext.getGameObject();
        return Arrays.stream(resolvingContext.getFactoryTypeNames())
                .map(type -> drawingModulePresenter.getActor(type, gameObject));
    }
}

