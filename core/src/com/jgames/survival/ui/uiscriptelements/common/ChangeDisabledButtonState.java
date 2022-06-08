package com.jgames.survival.ui.uiscriptelements.common;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;

public class ChangeDisabledButtonState implements UIRunnableScript<EmptyScriptState> {
    private final Button actor;

    public ChangeDisabledButtonState(Button actor) {
        this.actor = actor;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        actor.setDisabled(!actor.isDisabled());
    }
}
