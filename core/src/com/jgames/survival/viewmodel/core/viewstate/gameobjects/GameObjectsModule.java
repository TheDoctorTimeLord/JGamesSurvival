package com.jgames.survival.viewmodel.core.viewstate.gameobjects;

import java.util.HashMap;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.viewmodel.core.ViewModelException;
import com.jgames.survival.viewmodel.core.model.GameObject;
import com.jgames.survival.viewmodel.core.viewstate.ViewStateModule;

@Bean
public class GameObjectsModule implements ViewStateModule<GameObjectsPresenter>, GameObjectsPresenter {
    public static final String NAME = "objectsModule";

    private final Map<Integer, GameObject> gameObjectById = new HashMap<>();

    public GameObject registerGameObject(int id) {
        return gameObjectById.compute(id, (objectId, o) -> new GameObject(objectId));
    }

    public GameObject getOrCreateGameObject(int id) {
        return gameObjectById.computeIfAbsent(id, GameObject::new);
    }

    @Override
    public GameObject getObject(int id) {
        GameObject gameObject = gameObjectById.get(id);
        if (gameObject == null) {
            throw new ViewModelException("Object with id [%s] was not found".formatted(id));
        }
        return gameObject;
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public GameObjectsPresenter getPresenter() {
        return this;
    }
}
