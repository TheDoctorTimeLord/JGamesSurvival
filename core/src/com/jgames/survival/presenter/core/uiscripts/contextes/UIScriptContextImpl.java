package com.jgames.survival.presenter.core.uiscripts.contextes;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.UIScriptMachine;

public class UIScriptContextImpl implements UIScriptContext {
    private final UIScriptMachine scriptMachine;
    private UIAction dispatchedAction;

    public UIScriptContextImpl(UIScriptMachine scriptMachine) {
        this.scriptMachine = scriptMachine;
    }

    @Override
    @Null
    public UIAction getDispatchedAction() {
        return dispatchedAction;
    }

    @Override
    public UIScriptMachine getScriptMachine() {
        return scriptMachine;
    }

    public UIScriptContextImpl setDispatchedAction(UIAction dispatchedAction) {
        this.dispatchedAction = dispatchedAction;
        return this;
    }
}
