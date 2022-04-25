package com.jgames.survival.model;

public class GameConfiguration {
    private boolean isDebug = false;

    public boolean isDebug() {
        return isDebug;
    }

    public GameConfiguration setDebug(boolean debug) {
        isDebug = debug;
        return this;
    }
}
