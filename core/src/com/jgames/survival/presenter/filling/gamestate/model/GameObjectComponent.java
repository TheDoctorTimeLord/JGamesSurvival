package com.jgames.survival.presenter.filling.gamestate.model;

public abstract class GameObjectComponent implements Cloneable {
    @Override
    public GameObjectComponent clone() throws CloneNotSupportedException {
        return (GameObjectComponent)super.clone();
    }
}
