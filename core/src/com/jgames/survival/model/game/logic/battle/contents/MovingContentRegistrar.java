package com.jgames.survival.model.game.logic.battle.contents;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.contentregistrars.AbstractContentRegistrar;

import com.jgames.survival.model.game.logic.battle.events.changedirection.ChangeDirectionEventHandler;
import com.jgames.survival.model.game.logic.battle.events.changedirection.ChangeDirectionEventNotifier;
import com.jgames.survival.model.game.logic.battle.events.move.MoveEventNotifier;

@BattleBeanPrototype
public class MovingContentRegistrar extends AbstractContentRegistrar {
    @Override
    protected void registerInt() {
        registerPostHandler(new ChangeDirectionEventHandler(battleContext.getBattleState()));
        registerPostHandler(new ChangeDirectionEventNotifier(battleContext.getBattleActionRegistrar()));

        registerPostHandler(new MoveEventNotifier(battleContext.getBattleActionRegistrar()));
    }
}
