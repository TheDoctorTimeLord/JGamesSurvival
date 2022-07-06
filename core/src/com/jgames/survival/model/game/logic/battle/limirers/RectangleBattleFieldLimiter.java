package com.jgames.survival.model.game.logic.battle.limirers;

import java.util.HashSet;
import java.util.Set;

import ru.jengine.battlemodule.core.exceptions.BattleException;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;

public class RectangleBattleFieldLimiter implements BattlefieldLimiter {
    private final Point leftBottomVertex;
    private final Point rightTopVertex;

    public RectangleBattleFieldLimiter(Point leftBottomVertex, int width, int height) {
        this(leftBottomVertex, PointPool
                .obtain(leftBottomVertex.getX() + width - 1, leftBottomVertex.getY() + height - 1));
    }

    public RectangleBattleFieldLimiter(Point leftBottomVertex, Point rightTopVertex) {
        if (rightTopVertex.getX() < leftBottomVertex.getX() || rightTopVertex.getY() < leftBottomVertex.getY()) {
            throw new BattleException("Left bottom vertex [%s] and right top vertex [%s] must be swapped"
                    .formatted(leftBottomVertex, rightTopVertex));
        }

        this.leftBottomVertex = leftBottomVertex;
        this.rightTopVertex = rightTopVertex;
    }

    @Override
    public boolean inBound(Point point) {
        return leftBottomVertex.getX() <= point.getX() && leftBottomVertex.getY() <= point.getY()
                && point.getX() <= rightTopVertex.getX() && point.getY() <= rightTopVertex.getY();
    }

    @Override
    public Set<Point> getPointsInBound() {
        Set<Point> pointsInBound = new HashSet<>();

        for (int x = leftBottomVertex.getX(); x <= rightTopVertex.getX(); x++) {
            for (int y = leftBottomVertex.getY(); y <= rightTopVertex.getY(); y++) {
                pointsInBound.add(PointPool.obtain(x, y));
            }
        }

        return pointsInBound;
    }
}
