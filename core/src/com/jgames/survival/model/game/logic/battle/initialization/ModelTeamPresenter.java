package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.InitialBattleGenerator;

@BattleBeanPrototype
public class ModelTeamPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(BattleContext battleContext) {
        List<BattleAction> actions = new ArrayList<>();

        for (BattleModel battleModel : battleContext.getBattleState().getDynamicObjects()) {
            StringAttribute team = battleModel.getAttributes().getAsString(InitialBattleGenerator.TEAM_ATTRIBUTE);

            if (team != null) {
                actions.add(new ModelTeamAction(battleModel.getId(), team.getValue()));
            }
        }

        return actions;
    }
}
