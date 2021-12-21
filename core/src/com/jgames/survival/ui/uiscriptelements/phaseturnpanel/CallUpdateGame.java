package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.EmptyScriptState;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.model.GameActionSender;
import com.jgames.survival.model.api.actions.NewTurnBattleAction;

public class CallUpdateGame implements UIScriptElement<EmptyScriptState> {
    private final GameActionSender actionSender;

    public CallUpdateGame(GameActionSender actionSender) {
        this.actionSender = actionSender;
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
        actionSender.sendGameAction(new NewTurnBattleAction());
    }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
