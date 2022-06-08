package com.jgames.survival.model.api.interaction;

public interface GameChangeListenerRegistrar {
    void addGameChangesListener(GameChangeListener listener);
    void removeGameChangeListener(GameChangeListener listener);
}
