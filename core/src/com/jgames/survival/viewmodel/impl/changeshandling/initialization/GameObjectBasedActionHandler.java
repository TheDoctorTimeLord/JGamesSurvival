package com.jgames.survival.viewmodel.impl.changeshandling.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.viewmodel.core.viewstate.PresentingViewState;
import com.jgames.survival.viewmodel.core.viewstate.mutators.GameObjectsMutator;
import com.jgames.survival.viewmodel.impl.changeshandling.BattleActionHandler;

public abstract class GameObjectBasedActionHandler<A extends BattleAction> implements BattleActionHandler<A> {
    protected GameObjectsMutator gameObjectsMutator;

    @Override
    public void setGameState(PresentingViewState presentingViewState) {
        gameObjectsMutator = presentingViewState.getModuleMutator(GameObjectsMutator.class);
    }
}
