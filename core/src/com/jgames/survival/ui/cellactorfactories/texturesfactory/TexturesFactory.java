package com.jgames.survival.ui.cellactorfactories.texturesfactory;

import java.util.List;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.ui.UIException;

public class TexturesFactory implements CellActorFactory {
    private final List<TextureRegion> regions;
    private final TextureSelector selector;

    public TexturesFactory(List<TextureRegion> regions, TextureSelector selector)
    {
        this.regions = regions;
        this.selector = selector;
    }

    @Override
    public Actor create(@Nullable GameObject gameObject) throws UIException {
        return new Image(selector.select(regions, gameObject));
    }

    @FunctionalInterface
    public interface TextureSelector {
        TextureRegion select(List<TextureRegion> textures, @Nullable GameObject gameObject);
    }
}
