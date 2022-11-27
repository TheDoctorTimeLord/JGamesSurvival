package com.jgames.survival.viewmodel.impl.changeshandling;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.viewmodel.core.viewstate.PresentingViewState;

public interface BattleActionHandler<A extends BattleAction> {
    void setGameState(PresentingViewState presentingViewState);
    boolean canHandle(BattleAction battleAction);
    Runnable createApplyingTask(A battleAction);
}
