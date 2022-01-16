package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.UpdatedCellsPresenter;

public class UpdatedCellsModule implements PresentingStateModule<UpdatedCellsModule>, UpdatedCellsPresenter {
    public static final String NAME = "updatedCells";

    private final Deque<Set<Point>> updatedCells;

    public UpdatedCellsModule() {
        updatedCells = new ArrayDeque<>();
        updatedCells.addLast(Collections.emptySet());
    }

    public void addState() {
        updatedCells.addLast(new HashSet<>());
    }

    public synchronized void markCellAsUpdated(Point cellCoordinate) {
        updatedCells.getLast().add(cellCoordinate);
    }

    @Override
    public synchronized void updateToNextPhase() {
        updatedCells.removeFirst();
    }

    @Override
    public synchronized Collection<Point> getUpdatedCells() {
        return new HashSet<>(updatedCells.getFirst());
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public UpdatedCellsModule getPresenter() {
        return this;
    }
}
