package com.jgames.survival.model.game.logic.battle.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

public class ModelTeamAction  implements BattleAction {
    private final int modelId;
    private final String team;

    public ModelTeamAction(int modelId, String team) {
        this.modelId = modelId;
        this.team = team;
    }

    public int getModelId() {
        return modelId;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "ModelTeamAction{" +
                "modelId=" + modelId +
                ", team='" + team + '\'' +
                '}';
    }
}
