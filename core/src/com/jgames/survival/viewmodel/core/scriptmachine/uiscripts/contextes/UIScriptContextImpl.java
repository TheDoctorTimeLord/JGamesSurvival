package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.UIActionDispatcher;

public class UIScriptContextImpl implements UIScriptContext {
    private final UIActionDispatcher scriptMachine;
    private final UIAction dispatchedAction;

    public UIScriptContextImpl(UIActionDispatcher scriptMachine, @Null UIAction dispatchedAction) {
        this.scriptMachine = scriptMachine;
        this.dispatchedAction = dispatchedAction;
    }

    @Override
    @Null
    public UIAction getDispatchedAction() {
        return dispatchedAction;
    }

    @Override
    public UIActionDispatcher getActionDispatcher() {
        return scriptMachine;
    }
}
