package com.jgames.survival.utils;

import ru.jengine.utils.Logger;

import com.badlogic.gdx.Gdx;

public class GdxLogger implements Logger {
    @Override
    public void log(String prefix, String message) {
        Gdx.app.log(prefix, message);
    }

    @Override
    public void debug(String prefix, String message) {
        Gdx.app.debug(prefix, message);
    }

    @Override
    public void error(String prefix, String message) {
        Gdx.app.error(prefix, message);
    }

    @Override
    public void error(String prefix, String message, Throwable throwable) {
        Gdx.app.error(prefix, message, throwable);
    }

    @Override
    public void error(String prefix, Throwable throwable) {
        Gdx.app.error(prefix, throwable.getMessage(), throwable);
    }
}
