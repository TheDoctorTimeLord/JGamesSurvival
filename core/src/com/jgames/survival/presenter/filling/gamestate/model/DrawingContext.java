package com.jgames.survival.presenter.filling.gamestate.model;

import javax.annotation.Nullable;

/**
 * Метаданные отрисуемого объекта.
 */
public class DrawingContext {
    private GameObject gameObject;

    @Nullable
    public GameObject getGameObject() {
        return gameObject;
    }

    public DrawingContext setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
        return this;
    }
}
