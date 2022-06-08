package com.jgames.survival.model.api.gameload.battle;

import static com.jgames.survival.utils.GsonUtils.poolClassPath;

import java.lang.reflect.Type;

import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.jsonconverter.standardtools.JsonConverterDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@Bean
public class BattleAttributeDeserializer implements JsonConverterDeserializer<BattleAttribute> {
    @Override
    public BattleAttribute deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        Class<?> battleModelTypeClass = poolClassPath(jsonObject);
        return context.deserialize(jsonObject, battleModelTypeClass);
    }
}
