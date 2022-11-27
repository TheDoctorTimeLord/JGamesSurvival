package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl;

import ru.jengine.utils.Logger;

import com.jgames.survival.view.core.UIRuntimeException;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.UIScript;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContext;

public class UIScriptBasedElements implements UIScript {
    private final String scriptName;
    private final UIScriptElement[] scriptElements;
    private final UIScriptState scriptState;
    protected int currentElementIndex = 0;

    private Logger logger;

    public UIScriptBasedElements(String scriptName, UIScriptElement... scriptElements) {
        if (scriptElements.length == 0) {
            throw new UIRuntimeException("UI script must have more then 0 elements!");
        }

        this.scriptName = scriptName;
        this.scriptElements = scriptElements;
        this.scriptState = new UIScriptState();
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String getScriptName() {
        return scriptName;
    }

    @Override
    public Class<? extends UIAction> getWaitedAction() {
        return getCurrentScriptElement().getActivationAction();
    }

    @Override
    public boolean isValid(UIAction action) {
        UIScriptElement element = getCurrentScriptElement();
        Class<? extends UIAction> activationAction = element.getActivationAction();

        if (activationAction == null) {
            return true;
        }
        if (!activationAction.isInstance(action)) {
            return false;
        }

        try {
            return element.isValid(action);
        } catch (Exception e) {
            logger.error("UIScriptBasedElements", "Error while validation", e);
            return false;
        }
    }

    @Override
    public void handle(UIScriptContext context) {
        handleCurrentScriptElement(context);

        boolean wasEnded = true;

        while (setNextScriptElement()) {
            if (getCurrentScriptElement().getActivationAction() != null) {
                wasEnded = false;
                break;
            }
            handleCurrentScriptElement(context);
        }

        if (wasEnded) {
            resetScript();
        }
    }

    private void handleCurrentScriptElement(UIScriptContext context) {
        UIScriptElement currentScriptElement = getCurrentScriptElement();
        try {
            currentScriptElement.handle(context, scriptState);
        } catch (Exception e) {
            logger.error("UIScriptBasedElements", "Error while handling [%s]".formatted(currentScriptElement), e);
            rollback(context.getDispatchedAction());
        }
    }

    @Override
    public boolean rollback(UIAction action) {
        boolean wasRollback = false;

        do {
            UIScriptElement currentScriptElement = getCurrentScriptElement();
            if (!currentScriptElement.rollback(action, scriptState)) {
                break;
            }
            wasRollback = true;
        } while (setPreviousScriptElement());

        return wasRollback;
    }

    @Override
    public void resetScript() {
        scriptState.reset();
        currentElementIndex = 0;
    }

    private UIScriptElement getCurrentScriptElement() {
        return scriptElements[currentElementIndex];
    }

    protected boolean setNextScriptElement() {
        currentElementIndex++;
        if (currentElementIndex >= scriptElements.length) {
            currentElementIndex = 0;
            return false;
        }
        return true;
    }

    protected boolean setPreviousScriptElement() {
        currentElementIndex--;
        if (currentElementIndex < 0) {
            currentElementIndex = 0;
            return false;
        }
        return true;
    }
}
