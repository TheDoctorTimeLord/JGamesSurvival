package com.jgames.survival.control.uiscripts.sctipts;

import java.util.List;

import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.UIScriptState;

public class CyclicUIScript<S extends UIScriptState> extends UIScriptWithState<S> {
    public CyclicUIScript(List<UIScriptElement<S>> script, S state, String scriptName) {
        super(script, state, scriptName);
    }

    @Override
    protected void stopScript() {
        activeScriptPointer = 0;
    }
}
