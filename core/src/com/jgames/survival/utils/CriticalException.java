package com.jgames.survival.utils;

public class CriticalException extends RuntimeException {
    public CriticalException(String message) {
        super(message);
    }

    public CriticalException(String message, Throwable throwable) {
        super(throwable);
    }
}
