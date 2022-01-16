package com.jgames.survival.presenter.core.uiscripts;

import com.jgames.survival.presenter.core.uiscripts.sctipts.UIScript;

public interface UIScriptMachine {
    void registerScript(UIScript script);
    void deleteScript(String scriptName);
}
