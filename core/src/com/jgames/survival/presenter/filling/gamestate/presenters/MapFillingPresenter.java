package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.Collection;
import java.util.List;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;

public interface MapFillingPresenter extends ModulePresenter {
    Collection<Point> getUpdatedCells();

    /**
     * Получить список id объектов, которые находятся на этой клетке.
     */
    List<Integer> getIdsOnCell(Point point);

    void updateToNextPhase();
}
