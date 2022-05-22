package com.jgames.survival.model.game.logic.battle.events.changedirection;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

public class ChangeDirectionEventNotifier implements PostHandler<ChangeDirectionEvent> {
    private final BattleActionRegistrar battleActionRegistrar;

    public ChangeDirectionEventNotifier(BattleActionRegistrar battleActionRegistrar) {
        this.battleActionRegistrar = battleActionRegistrar;
    }

    @Override
    public Class<ChangeDirectionEvent> getHandlingEventType() {
        return ChangeDirectionEvent.class;
    }

    @Override
    public void handle(ChangeDirectionEvent event) {
        battleActionRegistrar.registerAction(event);
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }
}
