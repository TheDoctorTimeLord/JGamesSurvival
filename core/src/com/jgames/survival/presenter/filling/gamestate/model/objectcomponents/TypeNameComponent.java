package com.jgames.survival.presenter.filling.gamestate.model.objectcomponents;

import com.jgames.survival.presenter.core.model.GameObjectComponent;

public class TypeNameComponent extends GameObjectComponent {
    private final String name;

    public TypeNameComponent(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return name;
    }
}
