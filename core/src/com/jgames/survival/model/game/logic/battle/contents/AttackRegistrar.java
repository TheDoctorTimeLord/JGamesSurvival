package com.jgames.survival.model.game.logic.battle.contents;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.core.contentregistrars.AbstractContentRegistrar;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.state.BattleState;

import com.jgames.survival.model.game.logic.battle.events.deadmodel.DeadDynamicModelHandler;
import com.jgames.survival.model.game.logic.battle.events.deadmodel.DeadDynamicModelNotifier;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageEventHandler;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageEventNotifier;

@BattleBeanPrototype
public class AttackRegistrar extends AbstractContentRegistrar {
    @Override
    protected void registerInt() {
        BattleActionRegistrar actionRegistrar = battleContext.getBattleActionRegistrar();
        DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();
        BattleState battleState = battleContext.getBattleState();

        registerPostHandler(new DamageEventHandler(battleState, dispatcher));
        registerPostHandler(new DamageEventNotifier(actionRegistrar));

        registerPostHandler(new DeadDynamicModelHandler(battleContext.getTaskRegistrar(),
                battleContext.getBattleDynamicObjectsManager()));
        registerPostHandler(new DeadDynamicModelNotifier(actionRegistrar));
    }
}
