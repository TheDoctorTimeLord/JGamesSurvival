package com.jgames.survival.utils;

import java.util.HashMap;
import java.util.Map;

public class GameProperties {
    private final Map<String, Object> properties = new HashMap<>();

    public GameProperties() {}

    public GameProperties(Map<Object, Object> otherProperties, boolean withConvert) {
        otherProperties.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof String)
                .peek(entry -> entry.setValue(convertValue(entry.getValue(), withConvert)))
                .forEach(entry -> setProperty((String)entry.getKey(), entry.getValue()));
    }

    private static Object convertValue(Object value, boolean withConvert) {
        if (!withConvert) {
            return value;
        }

        if (value instanceof String v) {
            if (v.equals("true")) {
                return true;
            } else if (v.equals("false")) {
                return false;
            }
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public <V> V getProperty(String key) {
        return (V)properties.get(key);
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }
}
