package com.jgames.survival.model.game.logic.battle.events.bodypartdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

/**
 * Отправляет на регистрацию событие BodyPartDamageEvent
 */
public class BodyPartDamageEventNotifier implements PostHandler<BodyPartDamageEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public BodyPartDamageEventNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }

    @Override
    public Class<BodyPartDamageEvent> getHandlingEventType() {
        return BodyPartDamageEvent.class;
    }

    @Override
    public void handle(BodyPartDamageEvent event) {
        actionRegistrar.registerAction(event);
    }
}
