package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.game.logic.battle.events.DealingDamageNotification;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.ModelDataMutator;

public class DealingDamageNotificationHandler implements BattleActionHandler {
    private ModelDataMutator modelDataMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        modelDataMutator = presentingGameState.getModuleMutator(ModelDataMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof DealingDamageNotification;
    }

    @Override
    public void handle(BattleAction battleAction) {
        DealingDamageNotification notification = (DealingDamageNotification)battleAction;
        modelDataMutator.damageModel(notification.getTargetId(), notification.getDamagePoints());
    }
}
