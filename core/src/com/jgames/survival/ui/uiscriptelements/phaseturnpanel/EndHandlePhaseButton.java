package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonDataModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.PersonDataPresenter;

public class EndHandlePhaseButton implements UIRunnableScript<EmptyScriptState> {
    private final PersonDataPresenter personDataPresenter;
    private final TextButton turnButton;
    private final TextButton phaseButton;

    public EndHandlePhaseButton(PresentingGameState gameState, TextButton turnButton,
            TextButton phaseButton) {
        this.personDataPresenter = gameState.getModulePresenter(PersonDataModule.NAME);
        this.turnButton = turnButton;
        this.phaseButton = phaseButton;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        if (personDataPresenter.isLastPhase()) {
            turnButton.setDisabled(false);
            phaseButton.setDisabled(true);
        }
    }
}
