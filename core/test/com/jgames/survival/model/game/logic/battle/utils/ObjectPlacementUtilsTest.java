package com.jgames.survival.model.game.logic.battle.utils;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.limirers.SquareBattleFieldLimiter;

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
        Set<Point> mapPosition = battleFieldLimiter.getPointsInBound();;
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertNull(resultPoint);
    }

    @Test
    public void onlyOneCellFreeTest() {
        Set<Point> mapPosition = battleFieldLimiter.getPointsInBound();
        Point[] freePoint = gerFreeRandomPoints(mapPosition, 1);
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertEquals(freePoint[0], resultPoint);
    }

    @Test
    public void allCellsFreeTest() {
        Set<Point> mapPosition = new HashSet<>();
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        assert resultPoint != null;
        Assertions.assertTrue(battleFieldLimiter.inBound(resultPoint));
    }

    @Test
    public void twoCellsAreFreeTest() {
        Set<Point> mapPosition = battleFieldLimiter.getPointsInBound();
        Point[] freePoints = gerFreeRandomPoints(mapPosition, 2);
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertTrue(freePoints[0].equals(resultPoint)
                || freePoints[1].equals(resultPoint));
    }

    @Test
    public void isFreeCellTest() {
        Set<Point> mapPosition = generateSeveralPositions(10);
        Point resultPoint = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);
        Assertions.assertFalse(mapPosition.contains(resultPoint));
    }

    /**
     * Возвращает карту, на некоторых точках которой отсутсвуют объекты битвы.
     * @param count количество точек, на которых должны отсутсвовать объекты битвы
     */
    private static Set<Point> generateSeveralPositions(int count) {
        Set<Point> mapPosition = battleFieldLimiter.getPointsInBound();;
        gerFreeRandomPoints(mapPosition, count);
        return mapPosition;
    }

    /**
     * Возвращает координаты точек, на которых отсутствуют объекты битвы.
     * @param mapPosition карта, которая описывает расположение объектов на поле боя
     * @param count количество точкек, на которых должны отсутсвовать объекты битвы
     */
    private static Point[] gerFreeRandomPoints(Set<Point> mapPosition, int count) {
        Point[] freePoints = new Point[count];
        int rightBound = count % (MAP_SIZE * MAP_SIZE);
        if (rightBound == 0)
            rightBound += 1;
        for (int i = 0; i < rightBound; i++) {
            freePoints[i] = RandomUtils.chooseInCollection(mapPosition);
            mapPosition.remove(freePoints[i]);
        }
        return freePoints;
    }
}
