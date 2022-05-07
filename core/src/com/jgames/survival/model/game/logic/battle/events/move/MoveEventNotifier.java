package com.jgames.survival.model.game.logic.battle.events.move;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.battlemodule.standardfilling.movement.MoveEvent;
import ru.jengine.eventqueue.event.PostHandler;

public class MoveEventNotifier implements PostHandler<MoveEvent> {
    private final BattleActionRegistrar battleActionRegistrar;

    public MoveEventNotifier(BattleActionRegistrar battleActionRegistrar) {
        this.battleActionRegistrar = battleActionRegistrar;
    }

    @Override
    public Class<MoveEvent> getHandlingEventType() {
        return MoveEvent.class;
    }

    @Override
    public void handle(MoveEvent event) {
        battleActionRegistrar.registerAction(new MoveBattleAction(event));
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }
}
