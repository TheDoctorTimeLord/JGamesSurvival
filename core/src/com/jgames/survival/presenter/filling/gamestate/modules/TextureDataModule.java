package com.jgames.survival.presenter.filling.gamestate.modules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.TextureDataPresenter;

import java.util.HashMap;
import java.util.Map;

public class TextureDataModule implements PresentingStateModule<TextureDataPresenter>, TextureDataPresenter {
    public static final String NAME = "textureData";
    private final Map<String, TextureData> textureDataMap = new HashMap<>();

    @Override
    public Actor createActor(String name) {
        var textureFactory = textureDataMap.get(name).getTextureFactory();
        return textureFactory.getFrontWidget();
    }

    @Override
    public TextureData getTextureData(String name) {
        return textureDataMap.get(name);
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public TextureDataPresenter getPresenter() {
        return this;
    }
}
