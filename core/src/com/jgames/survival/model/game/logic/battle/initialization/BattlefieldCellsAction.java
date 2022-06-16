package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.Set;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

public class BattlefieldCellsAction implements BattleAction {
    private final Set<Point> battlefieldCells;

    public BattlefieldCellsAction(Set<Point> battlefieldCells) {
        this.battlefieldCells = battlefieldCells;
    }

    public Set<Point> getBattlefieldCells() {
        return battlefieldCells;
    }
}
