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
        Point topPoint = direction.getOffset();
        Point leftTopPoint = topPoint.add(direction.rotateLeft().getOffset());
        Point rightTopPoint = topPoint.add(direction.rotateRight().getOffset());

        return new Point[] { topPoint, leftTopPoint, rightTopPoint };
    }

    public static Point[] getFiveAroundOffsets(Direction direction) {
        Point[] frontOffsets = getThreeFrontOffsets(direction);
        Point[] offsets = Arrays.copyOf(frontOffsets, frontOffsets.length + 2);
        offsets[frontOffsets.length] = direction.rotateLeft().getOffset();
        offsets[frontOffsets.length + 1] = direction.rotateRight().getOffset();

        return offsets;
    }
}
