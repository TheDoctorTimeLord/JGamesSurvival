package com.jgames.survival.presenter.filling.gamestate.model;

public class ObjectType {
    private final String name;
    private final Integer id;

    public ObjectType(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}