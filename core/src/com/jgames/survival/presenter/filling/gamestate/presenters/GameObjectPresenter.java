package com.jgames.survival.presenter.filling.gamestate.presenters;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.model.ObjectType;
import com.jgames.survival.presenter.filling.gamestate.modules.GameObjectModule;

public interface GameObjectPresenter extends ModulePresenter {
    void addObject(Integer id, ObjectType objectType);

    ObjectType getBattleObject(Integer id);

    String getObjectName(Object object);
}
