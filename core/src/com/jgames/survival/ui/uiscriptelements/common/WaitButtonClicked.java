package com.jgames.survival.ui.uiscriptelements.common;

import java.util.Collections;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.filling.clickactions.ButtonClicked;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIWaitedScriptElement;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;

public class WaitButtonClicked implements UIWaitedScriptElement<EmptyScriptState> {
    private final TextButton waitedButton;

    public WaitButtonClicked(TextButton waitedButton) {
        this.waitedButton = waitedButton;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return Collections.singleton(ButtonClicked.class);
    }

    @Override
    public boolean isValid(UIAction action) {
        return action instanceof ButtonClicked
                && ((ButtonClicked)action).getClickedButton().equals(waitedButton);
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) { }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
