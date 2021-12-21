package com.jgames.survival.model.api.changes;

import java.util.List;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.api.GameChange;

public class ChangesForPhase implements GameChange {
    private final List<BattleAction> changes;

    public ChangesForPhase(List<BattleAction> changes) {
        this.changes = changes;
    }

    public List<BattleAction> getChanges() {
        return changes;
    }
}
