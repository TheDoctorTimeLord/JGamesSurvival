package com.jgames.survival.utils;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

public class MoveUtils {
    public static Direction getRotate(Point fromPosition, Point toPosition) {
        if (getDistance(fromPosition, toPosition) != 1) {
            throw new IllegalArgumentException("Points must be neighboring");
        }
        return Direction.getByOffset(toPosition.sub(fromPosition));
    }

    private static int getDistance(Point x, Point y) {
        int xOffset = y.getX() - x.getX();
        int yOffset = y.getY() - x.getY();
        return xOffset * xOffset + yOffset * yOffset;
    }
}
