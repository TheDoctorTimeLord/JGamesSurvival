package com.jgames.survival.control.uiscripts;

import java.util.List;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContext;
import com.jgames.survival.control.uiscripts.contextes.UIScriptContextImpl;

public class UIScript<S extends UIScriptState> { //TODO переделать под нормальную работу с сценариями
    private final List<UIScriptElement<S>> script;
    private final S state; //TODO переделать работу с состоянием
    private int activeScriptPointer = 0;

    public UIScript(List<UIScriptElement<S>> script, S state) {
        this.script = script;
        this.state = state;
    }

    public void handle(UIScriptContext context) { //TODO подумать о завершении сценария (сейчас они зациклены)
        UIScriptElement<S> element = getScriptElementWithIncrementPointer();
        element.handle(context, state);

        if (!getCurrentScriptElement().needWait()) {
            handle(new UIScriptContextImpl(context.getScriptMachine()));
        }

        if (activeScriptPointer == 0) { //TODO сделать не костыльно
            state.reset();
        }
    }

    public boolean isValid(UIAction action) {
        return getCurrentScriptElement().isValid(action);
    }

    public boolean needHandleOnStart() {
        return !script.isEmpty() && !script.get(0).needWait();
    }

    public void resetScript() {
        activeScriptPointer = 0;
        state.reset();
    }

    private UIScriptElement<S> getCurrentScriptElement() {
        return script.get(activeScriptPointer);
    }

    private UIScriptElement<S> getScriptElementWithIncrementPointer() {
        return script.get(getAndIncrementPointer());
    }

    private int getAndIncrementPointer() {
        int pointer = activeScriptPointer++;
        if (activeScriptPointer >= script.size()) {
            activeScriptPointer = 0;
        }
        return pointer;
    }
}
