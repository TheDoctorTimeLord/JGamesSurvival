package com.jgames.survival.model.game.logic.battle.contentregistrars;

import ru.jengine.battlemodule.core.contentregistrars.AbstractContentRegistrar;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.events.RangedAttackHandler;
import com.jgames.survival.model.game.logic.battle.events.RangedAttackNotifier;

@Bean
public class RangedAttackRegistrar extends AbstractContentRegistrar {
    @Override
    protected void registerInt() {
        registerPostHandler(new RangedAttackHandler(battleContext.getBattleState(), battleContext.getDispatcher(),
                battleContext.getTaskRegistrar(), battleContext.getBattleDynamicObjectsManager()));
        registerPostHandler(new RangedAttackNotifier(battleContext.getBattleActionRegistrar()));
    }
}
