package com.jgames.survival.control.uiscripts;

import com.jgames.survival.control.uiscripts.sctipts.UIScript;

public interface UIScriptMachine {
    void registerScript(UIScript script);
    void deleteScript(String scriptName);
}
