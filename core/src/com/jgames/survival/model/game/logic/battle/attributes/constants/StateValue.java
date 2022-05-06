package com.jgames.survival.model.game.logic.battle.attributes.constants;

public enum StateValue {
    DESTROYED, DAMAGED, GOOD;

    public boolean isLessOrEquals(int value) {
        return !isGreater(value);
    }

    public boolean isGreater(int value) {
        return ordinal() < value;
    }

    public static StateValue resolveByOrdinal(int ordinal) {
        StateValue[] values = StateValue.values();
        return 0 <= ordinal && ordinal < values.length ? values[ordinal] : null;
    }
}
