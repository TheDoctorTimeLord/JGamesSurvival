package com.jgames.survival.viewmodel.core.scriptmachine;

public interface UIActionDispatcher {
    <T extends UIAction> void dispatch(T action, CallbackAfterDispatch<T> callback);

    @FunctionalInterface
    interface CallbackAfterDispatch<T extends UIAction> {
        void run(T action);
    }
}
