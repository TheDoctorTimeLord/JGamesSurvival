package com.jgames.survival.model.game.logic.battle.utils;
import com.jgames.survival.model.game.logic.SquareBattleFieldLimiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;
import ru.jengine.utils.RandomUtils;

import java.util.*;

/**
 * Тестирование функционала нахождения координат свободной клетки, находящейся в пределах поля боя,
 * на которой отсутствуют объекты битвы.
 */
public class ObjectPlacementUtilsTest {
    private static final int MAP_SIZE = 5;
    private static final BattlefieldLimiter battleFieldLimiter =
            new SquareBattleFieldLimiter(PointPool.obtain(0, 0), MAP_SIZE);
    @Test
    public void allCellsOccupiedTest() {
        Map<Point, List<Integer>> mapPosition = generateAllPositions();
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertNull(resultPoint);
    }

    @Test
    public void onlyOneCellFreeTest() {
        Map<Point, List<Integer>> mapPosition = generateAllPositions();
        Point[] freePoint = gerFreeRandomPoints(mapPosition, 1);
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertEquals(freePoint[0], resultPoint);
    }

    @Test
    public void allCellsFreeTest() {
        Map<Point, List<Integer>> mapPosition = new HashMap<>();
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        assert resultPoint != null;
        Assertions.assertTrue(battleFieldLimiter.inBound(resultPoint));
    }

    @Test
    public void twoCellsAreFreeTest() {
        Map<Point, List<Integer>> mapPosition = generateAllPositions();
        Point[] freePoints = gerFreeRandomPoints(mapPosition, 2);
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertTrue(freePoints[0].equals(resultPoint)
                || freePoints[1].equals(resultPoint));
    }

    @Test
    public void isFreeCellTest() {
        Map<Point, List<Integer>> mapPosition = generateSeveralPositions(10);
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertFalse(mapPosition.containsKey(resultPoint));
    }

    /**
     * Возвращает карту, на всех точках которой расположены объекты битвы.
     */
    private static Map<Point, List<Integer>> generateAllPositions() {
        Map<Point, List<Integer>> mapPosition = new HashMap<>();
        Set<Point> points = battleFieldLimiter.getPointsInBound();
        int modelId = 1;
        for (Point point : points) {
            mapPosition.computeIfAbsent(point, p -> new ArrayList<>()).add(modelId);
            modelId++;
        }
        return mapPosition;
    }

    /**
     * Возвращает карту, на некоторых точках которой отсутсвуют объекты битвы.
     * @param count количество точек, на которых должны отсутсвовать объекты битвы
     */
    private static Map<Point, List<Integer>> generateSeveralPositions(int count) {
        Map<Point, List<Integer>> mapPosition = generateAllPositions();
        gerFreeRandomPoints(mapPosition, count);
        return mapPosition;
    }

    /**
     * Возвращает координаты точек, на которых отсутствуют объекты битвы.
     * @param mapPosition карта, которая описывает расположение объектов на поле боя
     * @param count количество точкек, на которых должны отсутсвовать объекты битвы
     */
    private static Point[] gerFreeRandomPoints(Map<Point, List<Integer>> mapPosition, int count) {
        Point[] freePoints = new Point[count];
        int rightBound = count % (MAP_SIZE * MAP_SIZE);
        if (rightBound == 0)
            rightBound += 1;
        for (int i = 0; i < rightBound; i++) {
            freePoints[i] = RandomUtils.chooseInCollection(mapPosition.keySet());
            mapPosition.remove(freePoints[i]);
        }
        return freePoints;
    }
}
