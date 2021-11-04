package com.jgames.survival.control.uiscripts.sctipts;

import java.util.List;
import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.UIScriptState;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContext;

public abstract class UIScriptWithState<S extends UIScriptState> implements UIScript {
    protected final List<UIScriptElement<S>> script;
    private final S state;
    private final String scriptName;
    protected int activeScriptPointer = 0;
    private int firstActionableElement = 0;

    public UIScriptWithState(List<UIScriptElement<S>> script, S state, String scriptName) {
        this.script = script;
        this.state = state;
        this.scriptName = scriptName;

        for (int i = 0; i < script.size(); i++) {
            if (!script.get(i).isRunnableElement()) {
                firstActionableElement = i;
                break;
            }
        }
    }

    @Override
    public String getScriptName() {
        return scriptName;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        if (activeScriptPointer <= firstActionableElement) {
            return script.get(firstActionableElement).getWaitedActions();
        }
        return getCurrentScriptElement().getWaitedActions();
    }

    @Override
    public boolean isActive() {
        return activeScriptPointer > firstActionableElement;
    }

    @Override
    public boolean isValid(UIAction action) {
        return getCurrentScriptElement().isValid(action);
    }

    @Override
    public void handle(UIScriptContext context) {
        UIScriptElement<S> element = getScriptElementWithIncrementPointer();
        element.handle(context, state);

        if (activeScriptPointer >= script.size()) {
            stopScript();
            return;
        }

        if (getCurrentScriptElement().isRunnableElement()) {
            handle(context);
        }
    }

    @Override
    public boolean rollback(UIAction action) {
        boolean wasChanged = false;

        for (; activeScriptPointer >= 0; activeScriptPointer--) {
            if (!getCurrentScriptElement().rollback(action, state)) {
                return wasChanged;
            }
            wasChanged = true;
        }
        activeScriptPointer = 0;

        return wasChanged;
    }

    @Override
    public void resetScript() {
        activeScriptPointer = 0;
        state.reset();
    }

    protected abstract void stopScript();

    private UIScriptElement<S> getCurrentScriptElement() {
        return script.get(activeScriptPointer);
    }

    private UIScriptElement<S> getScriptElementWithIncrementPointer() {
        return script.get(activeScriptPointer++);
    }
}
