package com.jgames.survival.ui.cellactorfactories;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.ui.UIException;

/**
 * Фабрика по производству картинок.
 */
public class TextureFactory implements CellActorFactory {
    private final TextureRegion textureRegion;

    public TextureFactory(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public Actor create(@Nullable GameObject gameObject) throws UIException {
        return new Image(textureRegion);
    }
}
