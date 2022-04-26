package com.jgames.survival.ui.cellactorfactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

public class HpLabelFactory extends AlignedLabelFactory {
    public HpLabelFactory() {
        super("", Align.topRight, Color.RED);
    }

    @Override
    public Actor create(DrawingContext drawingContext) {
        Label actor = (Label)super.create(drawingContext);
        actor.setText(drawingContext.getModelData().getHp() + "  ");
        return actor;
    }
}
