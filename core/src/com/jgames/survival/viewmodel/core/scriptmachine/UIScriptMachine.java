package com.jgames.survival.viewmodel.core.scriptmachine;

import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.UIScript;

public interface UIScriptMachine {
    void registerScript(UIScript script);
    void deleteScript(String scriptName);
}
