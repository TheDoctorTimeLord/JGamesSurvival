package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.gamechangeshangling.PresentingGameState;
import com.jgames.survival.control.uiscripts.EmptyScriptState;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public class EndHandlePhaseButton implements UIScriptElement<EmptyScriptState> {
    private final PresentingGameState gameState;
    private final TextButton turnButton;
    private final TextButton phaseButton;

    public EndHandlePhaseButton(PresentingGameState gameState, TextButton turnButton,
            TextButton phaseButton) {
        this.gameState = gameState;
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
        if (gameState.isPhasesEmpty()) {
            turnButton.setDisabled(false);
            phaseButton.setDisabled(true);
        }
    }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
