package com.jgames.survival.presenter.filling.gamestate.model.objectcomponents;

import com.jgames.survival.presenter.core.model.GameObjectComponent;

public class TeamComponent extends GameObjectComponent {
    private final String team;

    public TeamComponent(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}
