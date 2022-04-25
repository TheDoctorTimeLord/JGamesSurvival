package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.*;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;

public class MapFillingModule implements PresentingStateModule<MapFillingModule>, MapFillingPresenter {
    public static final String NAME = "mapFilling";
    private final Deque<Set<Point>> updatedCells;
    private final Deque<Map<Point, Collection<Integer>>> objectsOnCell;

    public MapFillingModule() {
        updatedCells = new ArrayDeque<>();
        objectsOnCell = new ArrayDeque<>();
        addState();
    }

    public void addState() {
        updatedCells.addLast(new HashSet<>());
        objectsOnCell.addLast(new HashMap<>());
    }

    public synchronized void markCellAsUpdated(Point cellCoordinate) {
        updatedCells.getLast().add(cellCoordinate);
    }

    public synchronized void updateObjectsOnCell(Point point, Collection<Integer> objectIds) {
        objectsOnCell.getLast().put(point, objectIds);
    }

    @Override
    public synchronized void updateToNextPhase() {
        if (!updatedCells.isEmpty() && !objectsOnCell.isEmpty()) {
            updatedCells.removeFirst();
            objectsOnCell.removeFirst();
        }
    }

    @Override
    public synchronized Collection<Point> getUpdatedCells() {
        return updatedCells.isEmpty() ? Collections.emptySet() : new HashSet<>(updatedCells.getFirst());
    }

    @Override
    public Collection<Integer> getIdsOnCell(Point point) {
        return objectsOnCell.isEmpty() ? Collections.emptyList() : objectsOnCell.getFirst().get(point);
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
