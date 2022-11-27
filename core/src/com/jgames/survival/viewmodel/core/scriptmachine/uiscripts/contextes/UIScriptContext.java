package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes;

import com.jgames.survival.viewmodel.core.scriptmachine.UIActionDispatcher;

public interface UIScriptContext extends UIScriptElementContext {
    UIActionDispatcher getActionDispatcher();
}
