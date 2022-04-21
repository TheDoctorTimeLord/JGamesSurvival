package com.jgames.survival.ui.actorfactories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jgames.survival.presenter.filling.factories.ActorFactory;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.utils.assets.TextureStorage;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;

public class CellBackground implements ActorFactory {
    private final Image cellBackgroundImage;

    public CellBackground(TextureStorage textureStorage) {
        cellBackgroundImage = new Image(textureStorage.createSprite(Constants.COMMON));
    }

    @Override
    public Actor create(DrawingContext drawingContext) {
        return cellBackgroundImage;
    }
}
