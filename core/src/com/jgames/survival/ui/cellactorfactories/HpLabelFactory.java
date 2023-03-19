package com.jgames.survival.ui.cellactorfactories;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.ui.UIException;

public class HpLabelFactory extends AlignedLabelFactory {
    public HpLabelFactory() {
        super("", Align.topRight, Color.RED);
    }

    @Override
    public Label create(@Nullable GameObject gameObject) throws UIException {
        Label actor = (Label)super.create(gameObject);
        if (gameObject == null) {
            throw new UIException("Game object is null but it is required");
        }

        HealthComponent healthComponent = gameObject.getComponent(HealthComponent.class);
        if (healthComponent == null) {
            throw new UIException("Game object [%s] has not component [%s] but it is required".formatted(gameObject, HealthComponent.class));
        }

        actor.setText(healthComponent.getHp() + "  ");
        return actor;
    }
}
