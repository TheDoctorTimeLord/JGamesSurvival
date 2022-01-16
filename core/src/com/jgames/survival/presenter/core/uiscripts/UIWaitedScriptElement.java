package com.jgames.survival.presenter.core.uiscripts;

public interface UIWaitedScriptElement<S extends UIScriptState> extends UIScriptElement<S> {
    default boolean isRunnableElement() {
        return false;
    }
}
