package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts;

import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContext;

public interface UIScriptHandler {
    boolean isValid(UIAction action);

    void handle(UIScriptContext context);
    boolean rollback(UIAction action);
}
