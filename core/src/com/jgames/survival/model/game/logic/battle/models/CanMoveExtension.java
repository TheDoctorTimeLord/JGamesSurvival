package com.jgames.survival.model.game.logic.battle.models;

import java.util.Set;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.movement.CanMoved;

public interface CanMoveExtension extends CanMoved {
    boolean hasAvailablePosition(BattleState battleState);
    Set<Point> getAvailablePosition(BattleState battleState);
}
