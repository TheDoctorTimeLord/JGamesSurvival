package com.jgames.survival.model.game.logic.battle.events.dealingdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

/**
 * Отправляет на регистрацию событие DealingDamageEvent
 */
public class DamageEventNotifier implements PostHandler<DamageEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public DamageEventNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }

    @Override
    public Class<DamageEvent> getHandlingEventType() {
        return DamageEvent.class;
    }

    @Override
    public void handle(DamageEvent event) {
        DamageNotification notification = new DamageNotification(event);
        actionRegistrar.registerAction(notification);
    }
}
