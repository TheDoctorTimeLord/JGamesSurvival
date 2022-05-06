package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.List;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;

public interface GameObjectsPresenter extends ModulePresenter {
    List<GameObject> getDataForAllObjects();
    GameObject getCurrentObjectState(int objectId);
    void updateToNextPhase();
    boolean isLastPhase();
}
