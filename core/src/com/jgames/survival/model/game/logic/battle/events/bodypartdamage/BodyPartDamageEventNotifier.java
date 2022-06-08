package com.jgames.survival.model.game.logic.battle.events.bodypartdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;

import com.jgames.survival.model.game.logic.battle.events.BaseBattlePostHandler;

/**
 * Отправляет на регистрацию событие BodyPartDamageEvent
 */
public class BodyPartDamageEventNotifier extends BaseBattlePostHandler<BodyPartDamageEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public BodyPartDamageEventNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }

    @Override
    public void handle(BodyPartDamageEvent event) {
        actionRegistrar.registerAction(event);
    }
}
