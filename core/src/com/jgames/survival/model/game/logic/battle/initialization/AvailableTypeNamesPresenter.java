package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;

@BattleBeanPrototype
public class AvailableTypeNamesPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(BattleContext battleContext) {
        return List.of(new AvailableTypeNamesAction(new ArrayList<>(
                        battleContext.getBattleState().getAvailableObjectTypeNames()
        )));
    }
}
