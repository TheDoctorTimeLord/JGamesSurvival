package com.jgames.survival.model.game.logic.battle.contents;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.contentregistrars.AbstractContentRegistrar;

import com.google.gson.JsonSerializer;
import com.jgames.survival.model.game.logic.battle.events.savebattle.SaveBattleEventHandler;

@BattleBeanPrototype
public class InfrastructureRegistrar extends AbstractContentRegistrar {
    private final List<JsonSerializer<?>> serializers;

    public InfrastructureRegistrar(List<JsonSerializer<?>> serializers) {
        this.serializers = serializers;
    }

    @Override
    protected void registerInt() {
        registerPostHandler(new SaveBattleEventHandler(battleContext.getBattleState(), serializers));
    }
}
