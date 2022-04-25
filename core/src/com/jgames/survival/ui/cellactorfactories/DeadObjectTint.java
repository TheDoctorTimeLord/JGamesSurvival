package com.jgames.survival.ui.cellactorfactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

public class DeadObjectTint implements CellActorFactory {
    private final Image deadObjectImage;

    public DeadObjectTint(Sprite object) {
        deadObjectImage = new Image(new TextureRegionDrawable(object).tint(new Color(0.9f, 0.9f, 0.9f, 0.5f)));
    }

    @Override
    public Actor create(DrawingContext drawingContext) {
        return deadObjectImage;
    }
}
