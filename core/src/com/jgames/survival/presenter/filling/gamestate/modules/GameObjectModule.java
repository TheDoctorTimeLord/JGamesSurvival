package com.jgames.survival.presenter.filling.gamestate.modules;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.ObjectType;
import com.jgames.survival.presenter.filling.gamestate.presenters.GameObjectPresenter;

import java.util.HashMap;
import java.util.Map;

public class GameObjectModule implements PresentingStateModule<GameObjectModule>, GameObjectPresenter {
    public static final String NAME = "gameObjectModule";
    private final Map<Integer, ObjectType> objectTypeMap = new HashMap<>();
    private final Map<Object, String> objectMap = new HashMap<>();

    @Override
    public void addObject(Integer id, ObjectType objectType) {
        objectTypeMap.put(id, objectType);
        objectMap.put(objectType, objectType.getName());
    }

    @Override
    public ObjectType getBattleObject(Integer id) {
        return objectTypeMap.get(id);
    }

    @Override
    public String getObjectName(Object object) {
        return objectMap.get(object);
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public GameObjectModule getPresenter() {
        return null;
    }
}
