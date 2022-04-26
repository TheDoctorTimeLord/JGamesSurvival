package com.jgames.survival.model.game.logic.battle.vision;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.visible.HasVision;
import ru.jengine.battlemodule.standardfilling.visible.outside.CustomRowRestriction;

public class StateBasedRowRestriction implements CustomRowRestriction {
    private BattleState battleState;
    private int maxDepth;

    @Override
    public void initialize(HasVision hasVision, BattleState battleState) {
        this.maxDepth = 5; //TODO должно считаться по персонажу hasVision.getVisionDistance()
        this.battleState = battleState;
    }

    @Override
    public boolean isAvailableDepth(int depth) {
        return depth <= maxDepth;
    }

    @Override
    public boolean isAvailablePoint(Point point) {
        return battleState.inBattlefieldBound(point);
    }
}
