package com.jgames.survival.control;

import java.util.ArrayList;
import java.util.List;

import com.jgames.survival.control.uiscripts.UIScript;
import com.jgames.survival.control.uiscripts.UIScriptMachine;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContextImpl;

public class SingleActiveScriptMachine implements UIScriptMachine, UIActionDispatcher {
    private final List<UIScript<?>> scripts = new ArrayList<>(); //TODO поменять на Array
    private int activeScriptPointer = -1;

    @Override
    public void registerScript(UIScript<?> script) {
        scripts.add(script);
        if (script.needHandleOnStart()) {
            script.handle(new UIScriptContextImpl(this));
        }
    }

    @Override
    public <T extends UIAction> void dispatch(T action, CallbackAfterDispatch<T> callback) {
        UIScript<?> activeScript = findActiveScript(action);
        if (activeScript != null) {
            activeScript.handle(new UIScriptContextImpl(this).setDispatchedAction(action));
        }
    }

    public void resetActiveScript() {
        if (activeScriptPointer != -1) {
            scripts.get(activeScriptPointer).resetScript();
            activeScriptPointer = -1;
        }
    }

    private UIScript<?> findActiveScript(UIAction action) {
        if (activeScriptPointer != -1) {
            if (scripts.get(activeScriptPointer).isValid(action)) {
                return scripts.get(activeScriptPointer);
            }
            resetActiveScript();
        }

        for (UIScript<?> script : scripts) {
            activeScriptPointer++;

            if (script.isValid(action)) {
                return script;
            }
        }

        activeScriptPointer = -1;
        return null;
    }
}
