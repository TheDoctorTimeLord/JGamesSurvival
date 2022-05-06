package com.jgames.survival.ui;

public class UIException extends RuntimeException {
    public UIException(String message) {
        super(message);
    }

    public UIException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
