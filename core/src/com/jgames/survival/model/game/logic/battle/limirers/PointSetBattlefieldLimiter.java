package com.jgames.survival.model.game.logic.battle.limirers;

import java.util.HashSet;
import java.util.Set;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;

public class PointSetBattlefieldLimiter implements BattlefieldLimiter {
    private final Set<Point> pointSet;

    public PointSetBattlefieldLimiter(Set<Point> pointSet) {
        this.pointSet = pointSet;
    }

    @Override
    public boolean inBound(Point point) {
        return pointSet.contains(point);
    }

    @Override
    public Set<Point> getPointsInBound() {
        return new HashSet<>(pointSet);
    }
}
