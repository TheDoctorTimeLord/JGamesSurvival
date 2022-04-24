package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelData;

public interface ModelDataPresenter extends ModulePresenter {
    List<ModelData> getDataForAllModels();
    ModelData getCurrentModelState(int personId);
    void updateToNextPhase();
    boolean isLastPhase();
    @Nullable
    Integer getModelOnPosition(Point position);
}
