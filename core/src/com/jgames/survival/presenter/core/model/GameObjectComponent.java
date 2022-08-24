package com.jgames.survival.presenter.core.model;

public abstract class GameObjectComponent implements Cloneable {
    @Override
    public GameObjectComponent clone() throws CloneNotSupportedException {
        return (GameObjectComponent)super.clone();
    }
}
