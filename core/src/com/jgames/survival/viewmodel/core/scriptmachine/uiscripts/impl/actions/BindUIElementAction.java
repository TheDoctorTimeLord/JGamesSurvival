package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.actions;

import com.jgames.survival.view.core.uielements.UIElementWrapper;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;

public class BindUIElementAction implements UIAction {
    private final UIElementWrapper uiElementWrapper;
    private final String qualifierName;

    public BindUIElementAction(UIElementWrapper uiElementWrapper, String qualifierName) {
        this.uiElementWrapper = uiElementWrapper;
        this.qualifierName = qualifierName;
    }

    public String getQualifierName() {
        return qualifierName;
    }

    public UIElementWrapper getUiElementWrapper() {
        return uiElementWrapper;
    }
}
