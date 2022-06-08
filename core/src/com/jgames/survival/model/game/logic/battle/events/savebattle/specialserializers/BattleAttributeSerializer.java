package com.jgames.survival.model.game.logic.battle.events.savebattle.specialserializers;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.reflections.ReflectionUtils;

import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jgames.survival.model.api.gameload.JsonApiConstants.Attributes;

@Bean
public class BattleAttributeSerializer implements JsonSerializer<BattleAttribute> {
    private final Logger logger;

    public BattleAttributeSerializer(Logger logger) {
        this.logger = logger;
    }

    @Override
    public JsonElement serialize(BattleAttribute src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty(Attributes.CLASS_PATH, src.getClass().getName());

        for (Field field : ReflectionUtils.getAllFields(src.getClass())) {
            try {
                field.setAccessible(true);
                object.add(field.getName(), context.serialize(field.get(src)));
            } catch (IllegalAccessException e) {
                logger.error("BattleAttributeSerializer", "Field [%s] is not accessible".formatted(field.getName()), e);
            }
        }

        return object;
    }
}
