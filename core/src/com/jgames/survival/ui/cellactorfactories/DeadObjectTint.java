package com.jgames.survival.ui.cellactorfactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.ui.UIException;

public class DeadObjectTint implements CellActorFactory {
    private final Drawable tint;

    public DeadObjectTint(Sprite object) {
        tint = new TextureRegionDrawable(object).tint(new Color(0.9f, 0.9f, 0.9f, 0.5f));
    }

    @Override
    public Actor create(DrawingContext drawingContext) throws UIException {
        return new Image(tint);
    }
}
