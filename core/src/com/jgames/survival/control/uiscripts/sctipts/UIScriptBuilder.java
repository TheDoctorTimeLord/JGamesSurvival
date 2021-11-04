package com.jgames.survival.control.uiscripts.sctipts;

import java.util.ArrayList;
import java.util.List;

import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.UIScriptState;

public class UIScriptBuilder<S extends UIScriptState> {
    private final String scriptName;
    private final S state;
    private final List<UIScriptElement<S>> scriptElements = new ArrayList<>();

    private UIScriptBuilder(String scriptName, S state) {
        this.scriptName = scriptName;
        this.state = state;
    }

    public UIScriptBuilder<S> addScriptElement(UIScriptElement<S> scriptElement) {
        scriptElements.add(scriptElement);
        return this;
    }

    public UIScriptWithState<S> buildCyclic() {
        return new CyclicUIScript<>(scriptElements, state, scriptName);
    }

    public static <S extends UIScriptState> UIScriptBuilder<S> builder(String scriptName, S state) {
        return new UIScriptBuilder<>(scriptName, state);
    }
}
