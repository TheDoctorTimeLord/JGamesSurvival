package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.EmptyScriptState;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public class EndHandleTurnButton implements UIScriptElement<EmptyScriptState> {
    private final TextButton turnButton;
    private final TextButton phaseButton;

    public EndHandleTurnButton(TextButton turnButton, TextButton phaseButton) {
        this.turnButton = turnButton;
        this.phaseButton = phaseButton;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return null;
    }

    @Override
    public boolean isRunnableElement() {
        return true;
    }

    @Override
    public boolean isValid(UIAction action) {
        return true;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        turnButton.setDisabled(true);
        phaseButton.setDisabled(false);
    }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
