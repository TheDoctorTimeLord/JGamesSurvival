package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;

@BattleBeanPrototype
public class BattlefieldCellsPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(BattleContext battleContext) {
        return List.of(new BattlefieldCellsAction(battleContext.getBattleState().getBattlefieldCells()));
    }
}
