package com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.initialization.BattlefieldCellsAction;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionHandler;
import com.jgames.survival.presenter.filling.gamestate.mutators.MapFillingMutator;

@Bean
public class BattlefieldCellsActionHandler implements BattleActionHandler {
    private MapFillingMutator mapFillingMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        mapFillingMutator = presentingGameState.getModuleMutator(MapFillingMutator.class);
    }

    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof BattlefieldCellsAction;
    }

    @Override
    public void handle(BattleAction battleAction) {
        BattlefieldCellsAction action = (BattlefieldCellsAction)battleAction;
        for (Point battlefieldCell : action.getBattlefieldCells()) {
            mapFillingMutator.addMapCellItem(battlefieldCell);
        }
    }
}
