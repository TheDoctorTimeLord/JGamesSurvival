package com.jgames.survival.model.game.logic.battle.events.savebattle.specialserializers;

import java.lang.reflect.Type;

import ru.jengine.battlemodule.core.models.BattleModelType;
import ru.jengine.beancontainer.annotations.Bean;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jgames.survival.model.api.gameload.JsonApiConstants.Attributes.BattleModel;
import com.jgames.survival.model.api.gameload.JsonApiConstants.ResourceNamespaces;

@Bean
public class BattleModelSerializer implements JsonSerializer<BattleModelType> {
    @Override
    public JsonElement serialize(BattleModelType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(convertToLink(src.getObjectTypeName()));
    }

    private static String convertToLink(String name) {
        return "%s:%s:%s".formatted(ResourceNamespaces.CORE_RESOURCES, BattleModel.BATTLE_OBJECT_TYPE, name);
    }
}
