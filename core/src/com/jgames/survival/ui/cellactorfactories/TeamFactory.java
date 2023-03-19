package com.jgames.survival.ui.cellactorfactories;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.TeamComponent;
import com.jgames.survival.ui.UIException;

public class TeamFactory extends AlignedLabelFactory {
    private final Color[] AVAILABLE_COLORS = {Color.BLACK, Color.BLUE, Color.BROWN, Color.RED, Color.DARK_GRAY};
    private int currentColor = 0;
    private final Map<String, Color> teamColors = new HashMap<>();

    public TeamFactory() {
        super(" T", Align.topLeft);
    }

    @Override
    public Label create(@Nullable GameObject gameObject) throws UIException {
        Label label = super.create(gameObject);
        label.getStyle().fontColor = getTeamColor(gameObject);
        return label;
    }

    private Color getTeamColor(GameObject gameObject) {
        if (gameObject == null) {
            return Color.CLEAR;
        }

        TeamComponent teamComponent = gameObject.getComponent(TeamComponent.class);
        if (teamComponent == null) {
            return Color.CLEAR;
        }

        String team = teamComponent.getTeam();
        Color color = teamColors.get(team);
        if (color != null) {
            return color;
        }

        Color chosedColor = AVAILABLE_COLORS[currentColor];
        teamColors.put(team, chosedColor);
        currentColor = (currentColor + 1) % AVAILABLE_COLORS.length;
        return chosedColor;
    }
}
