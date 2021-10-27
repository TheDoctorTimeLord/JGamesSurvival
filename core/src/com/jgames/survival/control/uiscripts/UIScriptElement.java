package com.jgames.survival.control.uiscripts;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public interface UIScriptElement<S extends UIScriptState> {
    boolean needWait();
    boolean isValid(UIAction action);
    void handle(UIScriptElementContext context, S state);
}
