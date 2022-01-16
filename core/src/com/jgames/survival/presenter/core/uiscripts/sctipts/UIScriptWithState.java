package com.jgames.survival.presenter.core.uiscripts.sctipts;

import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.UIScriptElement;
import com.jgames.survival.presenter.core.uiscripts.UIScriptState;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptContext;

public abstract class UIScriptWithState<S extends UIScriptState> implements UIScript {
    protected final UIScriptElement<S>[] script;
    private final S state;
    private final String scriptName;
    protected int activeScriptPointer = 0;
    protected int firstActionableElement = 0;

    @SafeVarargs
    public UIScriptWithState(String scriptName, S state, UIScriptElement<S>... script) {
        this.script = script;
        this.state = state;
        this.scriptName = scriptName;

        for (int i = 0; i < script.length; i++) {
            if (!script[i].isRunnableElement()) {
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
            return script[firstActionableElement].getWaitedActions();
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

        if (activeScriptPointer >= script.length) {
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
        return script[activeScriptPointer];
    }

    private UIScriptElement<S> getScriptElementWithIncrementPointer() {
        return script[activeScriptPointer++];
    }
}
