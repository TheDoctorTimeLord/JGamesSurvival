package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageNotification;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.GameObjectsMutator;

public class DealingDamageNotificationHandler implements BattleActionHandler {
    private GameObjectsMutator gameObjectsMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        gameObjectsMutator = presentingGameState.getModuleMutator(GameObjectsMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof DamageNotification;
    }

    @Override
    public void handle(BattleAction battleAction) {
        DamageNotification notification = (DamageNotification)battleAction;
        gameObjectsMutator.damageModel(notification.getTargetId(), notification.getDamagePoints());
    }
}
