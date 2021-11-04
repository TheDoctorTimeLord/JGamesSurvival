package com.jgames.survival.control.uiscripts.sctipts;

import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContext;

public interface UIScript {
    String getScriptName();
    Set<Class<? extends UIAction>> getWaitedActions();

    boolean isActive();
    boolean isValid(UIAction action);
    void handle(UIScriptContext context);

    boolean rollback(UIAction action);
    void resetScript();
}
