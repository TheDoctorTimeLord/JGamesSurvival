package com.jgames.survival.model.game.logic.battle.commands.meleeattackutils;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;

import java.util.ArrayList;
import java.util.List;

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
    public static List<Point> getNeighbour(Point modelPoint, BattleState battleState, Point[] offsets) {
        List<Point> neighbors = new ArrayList<>();
        for (Point offset : offsets) {
            Point pointNeighbor = modelPoint.add(offset);
            if (battleState.inBattlefieldBound(pointNeighbor)) {
                neighbors.add(pointNeighbor);
            }
        }
        return neighbors;
    }
}
