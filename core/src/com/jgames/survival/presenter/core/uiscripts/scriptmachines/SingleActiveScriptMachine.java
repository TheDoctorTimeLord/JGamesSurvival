package com.jgames.survival.presenter.core.uiscripts.scriptmachines;

import java.util.ArrayList;
import java.util.List;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptContextImpl;
import com.jgames.survival.presenter.core.uiscripts.sctipts.UIScript;

public class SingleActiveScriptMachine implements DispatcherUIScriptMachine {
    private final List<UIScript> scripts = new ArrayList<>();
    private int activeScriptPointer = -1;

    @Override
    public void registerScript(UIScript script) {
        scripts.add(script);
    }

    @Override
    public void deleteScript(String scriptName) {
        scripts.removeIf(script -> script.getScriptName().equals(scriptName));
    }

    @Override
    public <T extends UIAction> void dispatch(T action, CallbackAfterDispatch<T> callback) {
        UIScript activeScript = findActiveScript(action);
        if (activeScript != null) {
            activeScript.handle(new UIScriptContextImpl(this).setDispatchedAction(action));
        }
    }

    private UIScript findActiveScript(UIAction action) {
        if (activeScriptPointer != -1) {
            if (scripts.get(activeScriptPointer).isValid(action)) {
                return scripts.get(activeScriptPointer);
            }
            resetActiveScript();
        }

        for (UIScript script : scripts) {
            activeScriptPointer++;

            if (script.isValid(action)) {
                return script;
            }
        }

        activeScriptPointer = -1;
        return null;
    }

    private void resetActiveScript() {
        if (activeScriptPointer != -1) {
            scripts.get(activeScriptPointer).resetScript();
            activeScriptPointer = -1;
        }
    }
}
