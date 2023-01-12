package com.jgames.survival.view.core;

public class ViewException extends RuntimeException {
    public ViewException(String message) {
        super(message);
    }

    public ViewException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
