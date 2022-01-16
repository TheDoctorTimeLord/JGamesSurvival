package com.jgames.survival.presenter.core.uiscripts.sctipts;

import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptContext;

public interface UIScript {
    String getScriptName();
    Set<Class<? extends UIAction>> getWaitedActions();

    boolean isActive();
    boolean isValid(UIAction action);
    void handle(UIScriptContext context);

    boolean rollback(UIAction action);
    void resetScript();
}
