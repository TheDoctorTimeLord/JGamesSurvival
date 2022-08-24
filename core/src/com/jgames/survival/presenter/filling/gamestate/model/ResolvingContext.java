package com.jgames.survival.presenter.filling.gamestate.model;

import com.jgames.survival.presenter.core.model.GameObject;

/**
 * Описание объекта, который резолвится.
 */
public class ResolvingContext {
    private final String[] factoryTypeName;
    private final GameObject gameObject;

    public ResolvingContext(GameObject gameObject, String... factoryTypeName) {
        this.factoryTypeName = factoryTypeName;
        this.gameObject = gameObject;
    }

    public String[] getFactoryTypeNames() {
        return factoryTypeName;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
