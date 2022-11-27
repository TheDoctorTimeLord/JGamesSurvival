package com.jgames.survival.viewmodel.core.viewstate.gameobjects;

import com.jgames.survival.viewmodel.core.model.GameObject;
import com.jgames.survival.viewmodel.core.viewstate.ModulePresenter;

public interface GameObjectsPresenter extends ModulePresenter {
    GameObject getObject(int id);
}
