package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.events.move.MoveBattleAction;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.GameObjectsMutator;

@Bean
public class MoveBattleActionHandler implements BattleActionHandler {
    private GameObjectsMutator gameObjectsMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        gameObjectsMutator = presentingGameState.getModuleMutator(GameObjectsMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof MoveBattleAction;
    }

    @Override
    public void handle(BattleAction battleAction) {
        MoveBattleAction action = (MoveBattleAction)battleAction;
        gameObjectsMutator.moveObject(action.getModelId(), action.getNewPosition());
    }
}
