package com.jgames.survival.presenter.core.uiscripts;

import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;

public interface UIScriptElement<S extends UIScriptState> {
    Set<Class<? extends UIAction>> getWaitedActions();
    boolean isRunnableElement();

    boolean isValid(UIAction action);
    void handle(UIScriptElementContext context, S state);
    boolean rollback(UIAction action, S state);
}
