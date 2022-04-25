package com.jgames.survival.model.game.logic.battle.contents;

import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.core.contentregistrars.AbstractContentRegistrar;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.state.BattleState;

import com.jgames.survival.model.game.logic.battle.events.BodyPartDamageEventHandler;
import com.jgames.survival.model.game.logic.battle.events.BodyPartDamageEventNotifier;
import com.jgames.survival.model.game.logic.battle.events.DealingDamageEventHandler;
import com.jgames.survival.model.game.logic.battle.events.DealingDamageEventNotifier;

public class MeleeAttackRegistrar extends AbstractContentRegistrar {
    @Override
    protected void registerInt() {
        BattleActionRegistrar actionRegistrar = battleContext.getBattleActionRegistrar();
        DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();
        BattleState battleState = battleContext.getBattleState();

        registerPostHandler(new BodyPartDamageEventHandler(battleState, dispatcher));
        registerPostHandler(new BodyPartDamageEventNotifier(actionRegistrar));

        registerPostHandler(new DealingDamageEventHandler(battleState, battleContext.getTaskRegistrar(),
                battleContext.getBattleDynamicObjectsManager(), dispatcher));
        registerPostHandler(new DealingDamageEventNotifier(actionRegistrar));
    }
}
