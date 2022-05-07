package com.jgames.survival.model.game.logic.battle.contents;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.core.contentregistrars.AbstractContentRegistrar;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.state.BattleState;

import com.jgames.survival.model.game.logic.battle.events.bodypartdamage.BodyPartDamageEventHandler;
import com.jgames.survival.model.game.logic.battle.events.bodypartdamage.BodyPartDamageEventNotifier;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageEventHandler;
import com.jgames.survival.model.game.logic.battle.events.dealingdamage.DamageEventNotifier;

@BattleBeanPrototype
public class MeleeAttackRegistrar extends AbstractContentRegistrar {
    @Override
    protected void registerInt() {
        BattleActionRegistrar actionRegistrar = battleContext.getBattleActionRegistrar();
        DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();
        BattleState battleState = battleContext.getBattleState();

        registerPostHandler(new BodyPartDamageEventHandler(battleState, dispatcher));
        registerPostHandler(new BodyPartDamageEventNotifier(actionRegistrar));

        registerPostHandler(new DamageEventHandler(battleState, battleContext.getTaskRegistrar(),
                battleContext.getBattleDynamicObjectsManager(), dispatcher));
        registerPostHandler(new DamageEventNotifier(actionRegistrar));
    }
}
