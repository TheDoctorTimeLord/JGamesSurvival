package com.jgames.survival.control.uiscripts.sctipts;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.UIScriptState;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContext;

public class RunnableUIScript<S extends UIScriptState> extends UIScriptWithState<S> {
    public RunnableUIScript(List<UIScriptElement<S>> script, S state, String scriptName) {
        super(script, state, scriptName);
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
        return activeScriptPointer >= script.size();
    }
}
