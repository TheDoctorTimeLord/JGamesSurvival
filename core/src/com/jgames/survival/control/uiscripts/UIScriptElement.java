package com.jgames.survival.control.uiscripts;

import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public interface UIScriptElement<S extends UIScriptState> { //TODO сделать дробление на ранабл и не ранабл
    Set<Class<? extends UIAction>> getWaitedActions();
    boolean isRunnableElement();

    boolean isValid(UIAction action);
    void handle(UIScriptElementContext context, S state);
    boolean rollback(UIAction action, S state);
}
