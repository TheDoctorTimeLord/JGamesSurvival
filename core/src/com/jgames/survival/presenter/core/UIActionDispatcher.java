package com.jgames.survival.presenter.core;

public interface UIActionDispatcher {
    <T extends UIAction> void dispatch(T action, CallbackAfterDispatch<T> callback);

    @FunctionalInterface
    interface CallbackAfterDispatch<T extends UIAction> {
        void run(T action);
    }
}
