package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.game.logic.battle.initialization.ObjectTypeAction;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.ModelDataMutator;

public class ObjectTypeActionHandler implements BattleActionHandler {
    private ModelDataMutator modelDataMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        modelDataMutator = presentingGameState.getModuleMutator(ModelDataMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof ObjectTypeAction;
    }

    @Override
    public void handle(BattleAction battleAction) {
        ObjectTypeAction action = (ObjectTypeAction)battleAction;
        modelDataMutator.setObjectType(action.getModelId(), action.getModelTypeName());
    }
}
