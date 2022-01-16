package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.Collection;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;

public interface UpdatedCellsPresenter extends ModulePresenter {
    Collection<Point> getUpdatedCells();
    void updateToNextPhase();
}
