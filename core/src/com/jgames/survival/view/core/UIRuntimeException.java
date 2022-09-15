package com.jgames.survival.view.core;

public class UIRuntimeException extends RuntimeException {
    public UIRuntimeException(String message) {
        super(message);
    }

    public UIRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
