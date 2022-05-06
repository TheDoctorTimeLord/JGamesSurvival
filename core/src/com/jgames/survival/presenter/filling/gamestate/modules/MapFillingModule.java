package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.*;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.utils.CollectionUtils;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;

public class MapFillingModule implements PresentingStateModule<MapFillingModule>, MapFillingPresenter {
    public static final String NAME = "mapFilling";
    private final PhasedField<Set<Point>> updatedCells = new PhasedField<>(HashSet::new, s -> new HashSet<>(), true);
    private final PhasedFieldWithMap<Point, List<Integer>> objectsOnCell = new PhasedFieldWithMap<>(
            point -> new ArrayList<>(),
            ArrayList::new
    );

    public MapFillingModule() {
        addState();
    }

    public void addState() {
        updatedCells.duplicateLastState();
    }

    public void markCellAsUpdated(Point cellCoordinate) {
        updatedCells.getLastState().add(cellCoordinate);
    }

    public void addObjectsOnCell(Point point, int objectId) {
        objectsOnCell.getOrCreateLastState(point).add(objectId);
    }

    public void updateObjectsOnCell(Integer objectId, Point lastPosition, Point newPosition) {
        List<Integer> objectsOnPosition = objectsOnCell.getLastState(lastPosition);
        if (objectsOnPosition != null) {
            objectsOnPosition.remove(objectId);

            if (objectsOnPosition.isEmpty()) {
                objectsOnCell.removeFromLastState(lastPosition);
            }
        }

        objectsOnCell.getOrCreateLastState(newPosition).add(objectId);
    }

    @Override
    public void updateToNextPhase() {
        updatedCells.updateToNextPhase();
        objectsOnCell.updateToNextPhase();
    }

    @Override
    public Collection<Point> getUpdatedCells() {
        return updatedCells.isLastPhase() ? Collections.emptySet() : new HashSet<>(updatedCells.getCurrentState());
    }

    @Override
    public List<Integer> getIdsOnCell(Point point) {
        return (List<Integer>)CollectionUtils.getOrEmpty(objectsOnCell.getCurrentState(point));
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public MapFillingModule getPresenter() {
        return this;
    }
}
