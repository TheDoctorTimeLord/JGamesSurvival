package com.jgames.survival.model.game.logic.battle.events.dealingdamage;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;

import com.jgames.survival.model.game.logic.battle.events.BaseBattlePostHandler;

/**
 * Отправляет на регистрацию событие DealingDamageEvent
 */
public class DamageEventNotifier extends BaseBattlePostHandler<DamageEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public DamageEventNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }

    @Override
    public void handle(DamageEvent event) {
        actionRegistrar.registerAction(event);
    }
}
