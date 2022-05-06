package com.jgames.survival.ui.cellactorfactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.ui.UIException;

public class HpLabelFactory extends AlignedLabelFactory {
    public HpLabelFactory() {
        super("", Align.topRight, Color.RED);
    }

    @Override
    public Actor create(DrawingContext drawingContext) throws UIException {
        Label actor = (Label)super.create(drawingContext);
        GameObject gameObject = drawingContext.getGameObject();
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
