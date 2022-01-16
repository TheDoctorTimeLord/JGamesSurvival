package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;

public class EndHandleTurnButton implements UIRunnableScript<EmptyScriptState> {
    private final TextButton turnButton;
    private final TextButton phaseButton;

    public EndHandleTurnButton(TextButton turnButton, TextButton phaseButton) {
        this.turnButton = turnButton;
        this.phaseButton = phaseButton;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        turnButton.setDisabled(true);
        phaseButton.setDisabled(false);
    }
}
