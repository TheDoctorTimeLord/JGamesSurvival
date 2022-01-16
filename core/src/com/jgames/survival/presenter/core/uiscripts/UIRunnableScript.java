package com.jgames.survival.presenter.core.uiscripts;

import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;

@FunctionalInterface
public interface UIRunnableScript<S extends UIScriptState> extends UIScriptElement<S> {
    default Set<Class<? extends UIAction>> getWaitedActions() {
        return null;
    }

    default boolean isRunnableElement() {
        return true;
    }

    default boolean isValid(UIAction action) {
        return true;
    }

    @Override
    default boolean rollback(UIAction action, S state) {
        return true;
    }
}
