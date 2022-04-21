package com.jgames.survival.model.api.changes;

import com.jgames.survival.model.api.GameChange;

import java.util.HashMap;
import java.util.Map;

public class AvailableGameObject implements GameChange {
    private Object gameObject;
    private String gameObjectName;

    public AvailableGameObject(String gameObjectName, Object gameObject) {
        this.gameObject =gameObject;
        this.gameObjectName = gameObjectName;
    }

    public String getGameObjectName() {
        return gameObjectName;
    }

    public Object getGameObject() {
        return gameObject;
    }
}
