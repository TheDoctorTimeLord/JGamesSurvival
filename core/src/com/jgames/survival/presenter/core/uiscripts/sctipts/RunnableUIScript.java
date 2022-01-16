package com.jgames.survival.presenter.core.uiscripts.sctipts;

import java.util.Collections;
import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.UIScriptElement;
import com.jgames.survival.presenter.core.uiscripts.UIScriptState;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptContext;

public class RunnableUIScript<S extends UIScriptState> extends UIScriptWithState<S> {
    @SafeVarargs
    public RunnableUIScript(String scriptName, S state, UIScriptElement<S>... script) {
        super(scriptName, state, script);
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        if (wasStopped()) {
            return Collections.emptySet();
        }
        return super.getWaitedActions();
    }

    @Override
    public boolean isValid(UIAction action) {
        if (wasStopped()) {
            return false;
        }
        return super.isValid(action);
    }

    @Override
    public void handle(UIScriptContext context) {
        if (wasStopped()) {
            return;
        }
        super.handle(context);
    }

    @Override
    protected void stopScript() { }

    private boolean wasStopped() {
        return activeScriptPointer >= script.length;
    }
}
