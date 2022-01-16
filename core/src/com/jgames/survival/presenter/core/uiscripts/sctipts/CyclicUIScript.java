package com.jgames.survival.presenter.core.uiscripts.sctipts;

import com.jgames.survival.presenter.core.uiscripts.UIScriptElement;
import com.jgames.survival.presenter.core.uiscripts.UIScriptState;

public class CyclicUIScript<S extends UIScriptState> extends UIScriptWithState<S> {
    @SafeVarargs
    public CyclicUIScript(String scriptName, S state, UIScriptElement<S>... script) {
        super(scriptName, state, script);
    }

    @Override
    protected void stopScript() {
        activeScriptPointer = firstActionableElement;
    }
}
