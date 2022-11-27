package com.jgames.survival.viewmodel.core;

public class ViewModelException extends RuntimeException {
    public ViewModelException(String message) {
        super(message);
    }

    public ViewModelException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
