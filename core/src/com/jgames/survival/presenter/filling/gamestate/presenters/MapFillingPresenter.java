package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.utils.Pair;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;

public interface MapFillingPresenter extends ModulePresenter {
    Pair<Point, Point> getBattlefieldRectangleCoordinate();

    Collection<Point> getUpdatedCells();

    /**
     * Получить список id объектов, которые находятся на этой клетке.
     */
    List<Integer> getIdsOnCell(Point point);

    void updateToNextPhase();

    Set<Point> getBattlefieldCells();
}
