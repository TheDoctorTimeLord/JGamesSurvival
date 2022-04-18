package com.jgames.survival.ui.actorfactories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jgames.survival.presenter.filling.factories.ActorFactory;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

/**
 * Фабрика по производству картинок.
 */
public class TextureFactory implements ActorFactory {
    private final TextureRegion textureRegion;

    public TextureFactory(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public Actor create(DrawingContext drawingContext) {
        return new Image(textureRegion.getTexture());
    }
}
