package com.jgames.survival.model.game.logic.battle.events.deadmodel;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;

import com.jgames.survival.model.game.logic.battle.events.BaseBattlePostHandler;

public class DeadDynamicModelNotifier extends BaseBattlePostHandler<DeadDynamicModelEvent> {
    private final BattleActionRegistrar battleActionRegistrar;

    public DeadDynamicModelNotifier(BattleActionRegistrar battleActionRegistrar) {
        this.battleActionRegistrar = battleActionRegistrar;
    }

    @Override
    public void handle(DeadDynamicModelEvent event) {
        battleActionRegistrar.registerAction(event);
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }
}
