package com.jgames.survival.model.api.gameload.battle;

import static com.jgames.survival.utils.GsonUtils.extractObjectField;
import static com.jgames.survival.utils.GsonUtils.extractObjectFieldAsPrimitive;
import static com.jgames.survival.utils.GsonUtils.poolClassPath;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Set;

import org.reflections.ReflectionUtils;

import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.models.BattleModelType;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.jsonconverter.standardtools.JsonConverterDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@Bean
public class BattleModelDeserializer implements JsonConverterDeserializer<BattleModel> {
    private static final String BATTLE_MODEL_TYPE = "battleModelType";
    private static final String ID_FIELD = "id";
    private static final Set<String> IGNORED_FIELDS = Set.of(BATTLE_MODEL_TYPE, ID_FIELD, "attributes");

    @Override
    public BattleModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject battleModelTypeJson = extractObjectField(jsonObject, BATTLE_MODEL_TYPE, "objectTypeName", "attributesContainer");
        Class<?> battleModelTypeClass = poolClassPath(battleModelTypeJson);

        BattleModelType battleModelType = context.deserialize(battleModelTypeJson, battleModelTypeClass);
        BattleModel battleModel = battleModelType
                .createBattleModelByType(extractObjectFieldAsPrimitive(jsonObject, ID_FIELD).getAsInt());

        for (Field field : ReflectionUtils.getAllFields(battleModel.getClass(), f -> !IGNORED_FIELDS.contains(f.getName()))) {
            String fieldName = field.getName();

            JsonElement jsonElement = jsonObject.get(fieldName);
            if (jsonElement == null) {
                throw new JsonParseException("Field [%s] is not found. Json [%s]".formatted(fieldName, jsonObject.toString()));
            }

            try {
                field.setAccessible(true);
                field.set(battleModel, context.deserialize(jsonElement, field.getType()));
            }
            catch (IllegalAccessException e) {
                throw new JsonParseException("Field [%s] is not be set".formatted(field), e);
            }
        }

        return battleModel;
    }
}
