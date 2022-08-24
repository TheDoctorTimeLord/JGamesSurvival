package com.jgames.survival.ui.cellactorfactories;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.ui.UIException;

public class DeadObjectTint implements CellActorFactory {
    private final Drawable tint;

    public DeadObjectTint(Sprite object) {
        tint = new TextureRegionDrawable(object).tint(new Color(0.9f, 0.9f, 0.9f, 0.5f));
    }

    @Override
    public Actor create(@Nullable GameObject gameObject) throws UIException {
        return new Image(tint);
    }
}
