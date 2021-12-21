package com.jgames.survival.model.api.changes;

import java.util.Collection;

import ru.test.annotation.battle.battleactions.initializeBattle.StartPositionAction;

import com.jgames.survival.model.api.GameChange;

public class StartPositionData implements GameChange {
    private final Collection<StartPositionAction> startPositions;

    public StartPositionData(Collection<StartPositionAction> startPositions) {
        this.startPositions = startPositions;
    }

    public Collection<StartPositionAction> getStartPositions() {
        return startPositions;
    }
}
