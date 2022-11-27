package com.jgames.survival.viewmodel.core.model;

public class TypeNameComponent extends GameObjectComponent {
    private final String name;

    public TypeNameComponent(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return name;
    }
}
