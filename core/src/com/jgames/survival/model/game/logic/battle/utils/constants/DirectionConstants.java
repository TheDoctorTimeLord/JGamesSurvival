package com.jgames.survival.model.game.logic.battle.utils.constants;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Содержит константы, связанные с направлением взгляда персонажа
 */
public interface DirectionConstants {
    Point[] UP_OFFSETS = new Point[] {
            PointPool.obtain(-1, 1), PointPool.obtain(0, 1), PointPool.obtain(1, 1)};

    Point[] RIGHT_OFFSETS = new Point[] {
            PointPool.obtain(1, -1), PointPool.obtain(1, 0), PointPool.obtain(1, 1)};

    Point[] LEFT_OFFSETS = new Point[] {
            PointPool.obtain(-1, -1), PointPool.obtain(-1, 0), PointPool.obtain(-1, 1)};

    Point[] DOWN_OFFSETS = new Point[] {
            PointPool.obtain(-1, -1), PointPool.obtain(0, -1), PointPool.obtain(1, -1)};

    Map<Direction, Point[]> offsets = new LinkedHashMap<>(
            Map.of(
                    Direction.UP, UP_OFFSETS,
                    Direction.RIGHT, RIGHT_OFFSETS,
                    Direction.DOWN, DOWN_OFFSETS,
                    Direction.LEFT, LEFT_OFFSETS
            )
    );


}
