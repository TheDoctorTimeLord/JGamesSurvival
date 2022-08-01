package com.jgames.survival.presenter.filling.gamestate.model;

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
