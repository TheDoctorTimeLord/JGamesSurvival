package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.model.GameActionSender;
import com.jgames.survival.model.api.actions.NewTurnBattleAction;

/**
 * Исполняемый шаг скрипта, который вызывает действие обработки нового хода боя
 */
public class CallUpdateGame implements UIRunnableScript<EmptyScriptState> {
    private final GameActionSender actionSender;

    public CallUpdateGame(GameActionSender actionSender) {
        this.actionSender = actionSender;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        actionSender.sendGameAction(new NewTurnBattleAction());
    }
}
