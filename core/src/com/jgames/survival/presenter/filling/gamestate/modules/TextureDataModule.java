package com.jgames.survival.presenter.filling.gamestate.modules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.TextureDataPresenter;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.factories.ActorFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Модуль для хранения метаданных данных о текстуре и фабриках актёров.
 */
public class TextureDataModule implements PresentingStateModule<TextureDataPresenter>, TextureDataPresenter {
    public static final String NAME = "textureData";
    private final Map<String, BoundedTextureData> textureDataMap = new HashMap<>();
    private final Map<String, ActorFactory> actorFactoryMap = new HashMap<>();
    private final Actor defaultActor = null;

    /**
     * Привязать имя к метаданным.
     */
    public void addBoundingTextureData(BoundedTextureData textureData) {
        textureDataMap.put(textureData.getName(), textureData);
    }

    /**
     * Привязать имя к фабрике актёров.
     */
    public void addActorFactory(ActorFactory actorFactory) {
        actorFactoryMap.put(actorFactory.getActorName(), actorFactory);
    }

    @Override
    public Actor createActor(String name) {
        ActorFactory textureFactory = actorFactoryMap.get(name);
        if (textureFactory == null) return defaultActor;
        return textureFactory.create(new DrawingContext());
    }

    @Override
    public BoundedTextureData getTextureData(String name) {
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
