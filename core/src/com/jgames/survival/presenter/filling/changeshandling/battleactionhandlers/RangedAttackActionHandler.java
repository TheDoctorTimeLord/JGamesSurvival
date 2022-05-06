package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.game.logic.battle.events.RangedAttackEvent;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.GameObjectsMutator;

public class RangedAttackActionHandler implements BattleActionHandler {
    private GameObjectsMutator moduleMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
         moduleMutator = presentingGameState.getModuleMutator(GameObjectsMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof RangedAttackEvent;
    }

    @Override
    public void handle(BattleAction battleAction) {
        RangedAttackEvent event = (RangedAttackEvent)battleAction;
        moduleMutator.damageModel(event.getTargetId(), event.getDamagePoints());
    }
}
