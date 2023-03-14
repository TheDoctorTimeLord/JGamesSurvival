package com.jgames.survival.model.game.logic.battle.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;

/**
 * Описывает получение точек, которые находятся в пределах поля боя
 * и на определенном смещении относительно заданной точки
 */
public class LocationUtils {
    /**
     * Возвращает список соседей данной точки, которые удовлетворяют указанному смещению
     * и находятся в пределах поля боя
     * @param modelPoint точка, на которой находится персонаж
     * @param battleState текущее состояние битвы
     * @param offsets смещение, относительно которого нужно искать соседей @modelPoint
     */
    public static List<Point> getNeighbours(Point modelPoint, BattleState battleState, Point... offsets) {
        return Arrays.stream(offsets)
                .map(modelPoint::add)
                .filter(battleState::inBattlefieldBound)
                .collect(Collectors.toList());
    }

    public static Point[] getThreeFrontOffsets(Direction direction) {
        Point frontPoint = direction.getOffset();
        Point leftTopPoint = frontPoint.add(direction.rotateLeft().getOffset());
        Point rightTopPoint = frontPoint.add(direction.rotateRight().getOffset());

        return new Point[] { frontPoint, leftTopPoint, rightTopPoint };
    }

    public static Point[] getVerticalAndHorizontalOffsets(Direction direction) {
        Point frontPoint = direction.getOffset();
        Point leftPoint = direction.rotateLeft().getOffset();
        Point rightPoint = direction.rotateRight().getOffset();
        Point backPoint = direction.rotateLeft().rotateLeft().getOffset();

        return new Point[] { frontPoint, leftPoint, rightPoint, backPoint };
    }
}
