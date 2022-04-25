package com.jgames.survival.model.api.changes;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.api.GameChange;

public class BattleActionWrapper implements GameChange {
    private final BattleAction wrapped;

    public BattleActionWrapper(BattleAction wrapped) {
        this.wrapped = wrapped;
    }

    public BattleAction getWrapped() {
        return wrapped;
    }

    @Override
    public String toString() {
        return "Wrapped {%s}".formatted(wrapped);
    }
}
