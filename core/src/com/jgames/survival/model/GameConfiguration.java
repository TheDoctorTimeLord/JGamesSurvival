package com.jgames.survival.model;

import ru.jengine.utils.Logger;

public class GameConfiguration {
    private final Logger logger;
    private boolean isDebug = false;

    public GameConfiguration(Logger logger) {
        this.logger = logger;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public GameConfiguration setDebug(boolean debug) {
        isDebug = debug;
        return this;
    }

    public Logger getLogger() {
        return logger;
    }
}
