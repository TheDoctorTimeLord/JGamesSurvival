package com.jgames.survival.model.api.changes;

import java.util.Collection;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.game.logic.battle.initialization.StartPositionAction;

public class StartPositionData implements GameChange {
    private final Collection<StartPositionAction> startPositions;

    public StartPositionData(Collection<StartPositionAction> startPositions) {
        this.startPositions = startPositions;
    }

    public Collection<StartPositionAction> getStartPositions() {
        return startPositions;
    }
}
