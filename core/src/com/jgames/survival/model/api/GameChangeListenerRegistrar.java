package com.jgames.survival.model.api;

public interface GameChangeListenerRegistrar {
    void addGameChangesListener(GameChangeListener listener);
    void removeGameChangeListener(GameChangeListener listener);
}
