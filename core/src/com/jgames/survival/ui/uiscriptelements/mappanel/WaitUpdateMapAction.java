package com.jgames.survival.ui.uiscriptelements.mappanel;

import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIWaitedScriptElement;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;

public class WaitUpdateMapAction implements UIWaitedScriptElement<EmptyScriptState> {
    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return Set.of(UpdateMapAction.class);
    }

    @Override
    public boolean isValid(UIAction action) {
        return true;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) { }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
