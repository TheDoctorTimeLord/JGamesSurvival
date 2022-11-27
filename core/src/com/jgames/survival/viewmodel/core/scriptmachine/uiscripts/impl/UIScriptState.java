package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl;

import java.util.HashMap;
import java.util.Map;

public class UIScriptState {
    public static final String TOUCHED_ACTOR = "touchedActor";

    private final Map<String, Object> variables = new HashMap<>();

    public boolean put(String variableName, Object variableValue) {
        return variables.put(variableName, variableValue) != null;
    }

    public void remove(String variableName) {
        variables.remove(variableName);
    }

    public boolean contains(String... variableNames) {
        for (String variableName : variableNames) {
            if (!variables.containsKey(variableName)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String variableName) {
        return (T)variables.get(variableName);
    }

    /**
     * Полностью аналогичен {@link #get(String)}. Метод {@link #get(String)} нельзя использовать, если компилятор
     * Java сам не может вывести правильный тип переменной. Этот метод позволяет явно указать тип.
     */
    public <T> T getAs(String variableName, Class<T> as) {
        return get(variableName);
    }

    public void reset() {
        variables.clear();
    }
}
