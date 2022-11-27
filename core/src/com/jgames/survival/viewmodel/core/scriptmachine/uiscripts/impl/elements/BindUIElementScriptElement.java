package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.elements;

import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContext;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.UIScriptElement;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.UIScriptState;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.actions.BindUIElementAction;

public class BindUIElementScriptElement implements UIScriptElement {
    public static final String BINDING_UI_ELEMENT = "bindingUiElement";

    private final String expectedQualifierName;

    public BindUIElementScriptElement(String expectedQualifierName) {
        this.expectedQualifierName = expectedQualifierName;
    }

    @Override
    public Class<? extends UIAction> getActivationAction() {
        return BindUIElementAction.class;
    }

    @Override
    public boolean isValid(UIAction action) {
        return expectedQualifierName.equals(((BindUIElementAction)action).getQualifierName());
    }

    @Override
    public void handle(UIScriptContext context, UIScriptState state) {
        state.put(BINDING_UI_ELEMENT, ((BindUIElementAction)context.getDispatchedAction()).getUiElementWrapper());
    }

    @Override
    public boolean rollback(UIAction action, UIScriptState state) {
        state.remove(BINDING_UI_ELEMENT);
        return true;
    }
}
