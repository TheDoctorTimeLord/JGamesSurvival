package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;

public interface UIScriptElementContext {
    @Null
    UIAction getDispatchedAction();
}
