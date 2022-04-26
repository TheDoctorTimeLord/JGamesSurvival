package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.List;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;

public interface ModelDataPresenter extends ModulePresenter {
    List<ModelData> getDataForAllModels();
    ModelData getCurrentModelState(int personId);
    void updateToNextPhase();
    boolean isLastPhase();
}
