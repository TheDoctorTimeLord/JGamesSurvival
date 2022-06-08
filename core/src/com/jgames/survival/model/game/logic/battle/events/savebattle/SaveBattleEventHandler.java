package com.jgames.survival.model.game.logic.battle.events.savebattle;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.jgames.survival.model.game.logic.battle.events.BaseBattlePostHandler;
import com.jgames.survival.utils.ReflectionUtils;
import com.jgames.survival.utils.ReflectionUtils.LazyGenericTypesIterable;

public class SaveBattleEventHandler extends BaseBattlePostHandler<SaveBattleEvent> {
    private final BattleState battleState;
    private final Gson gson;

    public SaveBattleEventHandler(BattleState battleState, List<JsonSerializer<?>> serializers) {
        this.battleState = battleState;
        GsonBuilder gsonBuilder = new GsonBuilder();

        for (JsonSerializer<?> serializer : serializers) {
            LazyGenericTypesIterable iterable = new LazyGenericTypesIterable(serializer.getClass());
            Class<?> type = ReflectionUtils.getGenericInterfaceType(iterable, JsonSerializer.class, 0);
            gsonBuilder.registerTypeHierarchyAdapter(type, serializer);
        }

        gsonBuilder.enableComplexMapKeySerialization();
        this.gson = gsonBuilder.create();
    }

    @Override
    public void handle(SaveBattleEvent event) {
        try (OutputStreamWriter w = new OutputStreamWriter(event.getOutput())) {
            gson.toJson(battleState, w);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }
}
