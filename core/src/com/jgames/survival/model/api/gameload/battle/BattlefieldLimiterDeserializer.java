package com.jgames.survival.model.api.gameload.battle;

import static com.jgames.survival.utils.deserialization.GsonUtils.extractObjectField;

import java.lang.reflect.Type;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.jsonconverter.standardtools.JsonConverterDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jgames.survival.model.game.logic.battle.limirers.SquareBattleFieldLimiter;

@Bean
public class BattlefieldLimiterDeserializer implements JsonConverterDeserializer<BattlefieldLimiter> {
    @Override
    public BattlefieldLimiter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject leftBottomVertex = extractObjectField(jsonObject, "leftBottomVertex", "x", "y");
        return new SquareBattleFieldLimiter(
                context.deserialize(leftBottomVertex, Point.class),
                jsonObject.getAsJsonPrimitive("side").getAsInt()
        );
    }
}
