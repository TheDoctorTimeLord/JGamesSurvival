package com.jgames.survival.model.game.logic.battle.events.deadmodel;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

public class DeadDynamicModelNotifier implements PostHandler<DeadDynamicModelEvent> {
    private final BattleActionRegistrar battleActionRegistrar;

    public DeadDynamicModelNotifier(BattleActionRegistrar battleActionRegistrar) {
        this.battleActionRegistrar = battleActionRegistrar;
    }

    @Override
    public Class<DeadDynamicModelEvent> getHandlingEventType() {
        return DeadDynamicModelEvent.class;
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
