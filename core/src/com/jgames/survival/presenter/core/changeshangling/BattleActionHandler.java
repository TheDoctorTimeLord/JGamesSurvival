package com.jgames.survival.presenter.core.changeshangling;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.presenter.core.gamestate.PresentingGameState;

public interface BattleActionHandler {
    void setGameState(PresentingGameState presentingGameState);
    boolean canHandle(BattleAction battleAction);
    void handle(BattleAction battleAction);
}
