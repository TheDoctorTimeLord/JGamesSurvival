package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.game.logic.battle.initialization.ModelHpAction;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.ModelDataMutator;

public class ModelHpActionHandler implements BattleActionHandler {
    private ModelDataMutator modelDataMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        modelDataMutator = presentingGameState.getModuleMutator(ModelDataMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof ModelHpAction;
    }

    @Override
    public void handle(BattleAction battleAction) {
        ModelHpAction action = (ModelHpAction)battleAction;
        modelDataMutator.setHp(action.getModelId(), action.getHp());
    }
}
