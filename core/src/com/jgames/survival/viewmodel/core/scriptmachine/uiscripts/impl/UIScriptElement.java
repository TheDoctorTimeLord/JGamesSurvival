package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContext;

public interface UIScriptElement {
    @Null Class<? extends UIAction> getActivationAction();

    boolean isValid(UIAction action);
    void handle(UIScriptContext context, UIScriptState state);
    boolean rollback(UIAction action, UIScriptState state);
}
