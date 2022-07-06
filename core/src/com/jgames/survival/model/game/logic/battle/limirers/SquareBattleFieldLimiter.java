package com.jgames.survival.model.game.logic.battle.limirers;

import java.util.HashSet;
import java.util.Set;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;

/**
 * Описывает ограничение на квадратные размеры поля боя.
 */
public class SquareBattleFieldLimiter implements BattlefieldLimiter {
    private final Point leftBottomVertex;
    private final int side;

    /**
     * Проверяет, находится ли точка в пределах поля боя, ограничиваемого текущим ограничителем.
     * @param leftBottomVertex левая нижня точка поля боя.
     * @param side сторона поля боя.
     */
    public SquareBattleFieldLimiter(Point leftBottomVertex, int side) {
        this.leftBottomVertex = leftBottomVertex;
        this.side = side;
    }

    /**
     * Проверяет, находится ли точка в пределах поля боя, ограничиваемого текущим ограничителем
     * @param point проверяемая точка
     * @return true - если точка удовлетворяет условиям текущего ограничителя, false - иначе
     */
    @Override
    public boolean inBound(Point point) {
        Point dist = point.sub(leftBottomVertex);
        return 0 <= dist.getX() && dist.getX() < side && 0 <= dist.getY() && dist.getY() < side;
    }

    /**
     * Возвращает все точки, находящиеся в пределах поля боя
     */
    @Override
    public Set<Point> getPointsInBound() {
        Set<Point> inBound = new HashSet<>();

        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                inBound.add(PointPool.obtain(x + leftBottomVertex.getX(), y + leftBottomVertex.getY()));
            }
        }

        return inBound;
    }
}
