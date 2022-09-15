package com.jgames.survival.view.core;

public class UIException extends Exception {
    public UIException(UIRuntimeException uiRuntimeException) {
        this(uiRuntimeException.getMessage(), uiRuntimeException.getCause());
    }

    public UIException(String message) {
        super(message);
    }

    public UIException(String message, Throwable cause) {
        super(message, cause);
    }
}
