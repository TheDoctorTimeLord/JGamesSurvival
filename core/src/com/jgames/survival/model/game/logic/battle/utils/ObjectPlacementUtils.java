package com.jgames.survival.model.game.logic.battle.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;
import ru.jengine.utils.RandomUtils;

/**
 * Описывает получение координат клетки поля боя, находящейся в пределах поля боя, на которой отсутствуют объекты битвы.
 */
public class ObjectPlacementUtils {
    private static final Point[] OFFSETS = new Point[] {
            PointPool.obtain(-1, -1), PointPool.obtain(-1, 0), PointPool.obtain(-1, 1),
            PointPool.obtain(0, -1), PointPool.obtain(0, 0), PointPool.obtain(0, 1),
            PointPool.obtain(1, -1), PointPool.obtain(1, 0), PointPool.obtain(1, 1)
    };

    /**
     * Возвращает координаты точки, находящейся в пределах поля боя,
     * заданного текущим ограничителем, на которой отсутствуют объекты битвы. Null, если такой точки нет.
     * @param filledCells карта, которая описывает расположение объектов на поле боя.
     * @param battleFieldLimiter ограничитель поля боя
     */
    @Nullable
    public static Point getFreeCell(Set<Point> filledCells, BattlefieldLimiter battleFieldLimiter) {
        Point point = getRandomPoint(battleFieldLimiter);
        if (filledCells.contains(point)) {
            Point freePoint = getFreeCellWithBFS(filledCells, point, battleFieldLimiter);
            if (freePoint == null) {
                //TODO логировать:
                System.out.print("На поле закончились свободные клетки!");
                return null;
            }
            return freePoint;
        }
        return point;
    }

    /**
     * Возвращает координаты произвольной точки, которая находится в пределах поля боя,
     * заданного текущим ограничителем.
     * @param battleFieldLimiter ограничитель поля боя
     */
    private static Point getRandomPoint(BattlefieldLimiter battleFieldLimiter) {
        Set<Point> availablePoints =  battleFieldLimiter.getPointsInBound();
        return RandomUtils.chooseInCollection(availablePoints);
    }

    /**
     * Возвращает координаты точки, находящейся в пределах поля боя, заданного текущим ограничителем,
     * на которой отсутствуют объекты битвы, с помощью алгоритма "Breadth-First Search (BFS)".
     * Null, если такой точки нет.
     * В качестве начальной точки для алгоритма берётся точка, на которой присутствует объект боя.
     * @param mapPosition карта, которая описывает расположение объектов на поле боя
     * @param point точка, на которой присутствует объект боя
     * @param battleFieldLimiter текущий ограничитель поля боя
     */
    @Nullable
    private static Point getFreeCellWithBFS(Set<Point> mapPosition, Point point,
                                            BattlefieldLimiter battleFieldLimiter) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        queue.add(point);
        visited.add(point);
        while (!queue.isEmpty())
        {
            point = queue.poll();
            List<Point> pointNeighbors = getPointNeighbors(point, battleFieldLimiter);
            for (Point neighbor : pointNeighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    if (!mapPosition.contains(neighbor)) {
                        return neighbor;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Возвращает список соседей данной точки, которые находятся в пределах поля боя,
     * заданного текущим ограничителем.
     * @param point точка, соседей которой нужно найти
     * @param battleFieldLimiter ограничитель поля боя
     */
    private static List<Point> getPointNeighbors(Point point, BattlefieldLimiter battleFieldLimiter) {
        List<Point> neighbors = new ArrayList<>();
        for (Point offset : OFFSETS) {
            Point pointNeighbor = point.add(offset);
            if (battleFieldLimiter.inBound(pointNeighbor) && !point.equals(pointNeighbor)) {
                neighbors.add(pointNeighbor);
            }
        }
        return neighbors;
    }
}