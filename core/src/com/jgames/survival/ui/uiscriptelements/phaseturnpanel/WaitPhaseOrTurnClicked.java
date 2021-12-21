package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import java.util.Collections;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.actions.PhaseOrTurnClicked;
import com.jgames.survival.control.uiscripts.EmptyScriptState;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public class WaitPhaseOrTurnClicked implements UIScriptElement<EmptyScriptState> {
    private final TextButton waitedButton;

    public WaitPhaseOrTurnClicked(TextButton waitedButton) {
        this.waitedButton = waitedButton;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return Collections.singleton(PhaseOrTurnClicked.class);
    }

    @Override
    public boolean isRunnableElement() {
        return false;
    }

    @Override
    public boolean isValid(UIAction action) {
        return action instanceof PhaseOrTurnClicked
                && ((PhaseOrTurnClicked)action).getClickedButton().equals(waitedButton);
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) { }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
