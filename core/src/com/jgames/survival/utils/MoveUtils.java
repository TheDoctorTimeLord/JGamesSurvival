package com.jgames.survival.utils;

import ru.jengine.battlemodule.core.serviceclasses.Point;

public class MoveUtils {
    public static int getDistance(Point x, Point y) {
        int xOffset = y.getX() - x.getX();
        int yOffset = y.getY() - x.getY();
        return xOffset * xOffset + yOffset * yOffset;
    }
}
