package com.jgames.survival.viewmodel.core.model;

public abstract class GameObjectComponent implements Cloneable {
    @Override
    public GameObjectComponent clone() throws CloneNotSupportedException {
        return (GameObjectComponent)super.clone();
    }
}
