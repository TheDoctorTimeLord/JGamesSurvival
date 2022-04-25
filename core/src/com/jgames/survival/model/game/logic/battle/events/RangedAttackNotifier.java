package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

public class RangedAttackNotifier implements PostHandler<RangedAttackEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public RangedAttackNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public Class<RangedAttackEvent> getHandlingEventType() {
        return RangedAttackEvent.class;
    }

    @Override
    public void handle(RangedAttackEvent event) {
        actionRegistrar.registerAction(event);
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }
}
