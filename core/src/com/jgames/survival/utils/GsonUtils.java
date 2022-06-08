package com.jgames.survival.utils;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.jgames.survival.model.api.gameload.JsonApiConstants.Attributes;

public class GsonUtils {
    public static JsonPrimitive extractObjectFieldAsPrimitive(JsonObject object, String field) {
        JsonElement value = object.get(field);
        if (!value.isJsonPrimitive()) {
            throw new JsonParseException("Field [%s] must be primitive".formatted(field));
        }

        return value.getAsJsonPrimitive();
    }

    public static JsonObject extractObjectField(JsonObject object, String field, String... innerExistingFields) throws JsonParseException {
        JsonElement value = object.get(field);
        if (value == null || !value.isJsonObject()) {
            throw new JsonParseException("Field [%s] must be object. Json [%s]".formatted(field, object));
        }

        JsonObject extractedObject = value.getAsJsonObject();
        for (String innerExistingField : innerExistingFields) {
            if (!extractedObject.has(innerExistingField)) {
                throw new JsonParseException("Json doesn't have field [%s]. Json [%s]".formatted(field, object));
            }
        }

        return extractedObject;
    }

    public static Class<?> poolClassPath(JsonObject object) throws JsonParseException {
        JsonElement classPath = object.remove(Attributes.CLASS_PATH);
        if (classPath == null || !classPath.isJsonPrimitive()) {
            throwClassPathIsIncorrect(classPath, object);
        }

        JsonPrimitive classPathPrimitive = classPath.getAsJsonPrimitive();
        if (!classPathPrimitive.isString()) {
            throwClassPathIsIncorrect(classPathPrimitive, object);
        }

        String classPathStr = classPathPrimitive.getAsString();

        try {
            return GsonUtils.class.getClassLoader().loadClass(classPathStr);
        }
        catch (ClassNotFoundException e) {
            throw new JsonParseException("Class path is incorrect", e);
        }
    }

    private static void throwClassPathIsIncorrect(@Nullable JsonElement classPath, JsonElement element) {
        throw new JsonParseException("Class path is incorrect. ClassPath [%s] in [%s]"
                .formatted(classPath == null ? null : classPath.toString(), element.toString()));
    }
}
