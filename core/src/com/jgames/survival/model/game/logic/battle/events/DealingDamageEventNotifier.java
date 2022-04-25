package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

/**
 * Отправляет на регистрацию событие DealingDamageEvent
 */
public class DealingDamageEventNotifier implements PostHandler<DealingDamageEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public DealingDamageEventNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }

    @Override
    public Class<DealingDamageEvent> getHandlingEventType() {
        return DealingDamageEvent.class;
    }

    @Override
    public void handle(DealingDamageEvent event) {
        DealingDamageNotification notification = new DealingDamageNotification(event);
        actionRegistrar.registerAction(notification);
    }
}
