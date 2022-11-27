package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;

public interface RunnableUISScriptElement extends UIScriptElement {
    default @Null Class<? extends UIAction> getActivationAction() {
        return null;
    }

    default boolean isValid(UIAction action) {
        return true;
    }

    default boolean rollback(UIAction action, UIScriptState state) {
        return true;
    }
}
