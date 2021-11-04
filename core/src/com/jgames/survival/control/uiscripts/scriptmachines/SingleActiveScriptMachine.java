package com.jgames.survival.control.uiscripts.scriptmachines;

import java.util.ArrayList;
import java.util.List;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContextImpl;
import com.jgames.survival.control.uiscripts.sctipts.UIScript;

public class SingleActiveScriptMachine implements DispatcherUIScriptMachine {
    private final List<UIScript> scripts = new ArrayList<>(); //TODO поменять на Array
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
