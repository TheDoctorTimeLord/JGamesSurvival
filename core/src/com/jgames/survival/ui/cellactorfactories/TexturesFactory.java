package com.jgames.survival.ui.cellactorfactories;

import java.util.List;
import java.util.function.BiFunction;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

public class TexturesFactory implements CellActorFactory {
    private final List<TextureRegion> regions;
    private final BiFunction<List<TextureRegion>, DrawingContext, TextureRegion> selector;

    public TexturesFactory(List<TextureRegion> regions,
            BiFunction<List<TextureRegion>, DrawingContext, TextureRegion> selector)
    {
        this.regions = regions;
        this.selector = selector;
    }

    @Override
    public Actor create(DrawingContext drawingContext) {
        return new Image(selector.apply(regions, drawingContext));
    }
}
