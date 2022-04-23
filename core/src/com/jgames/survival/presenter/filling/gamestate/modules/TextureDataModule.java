package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.presenters.TextureDataPresenter;

/**
 * Модуль для хранения метаданных данных о текстуре и фабриках актёров.
 */
public class TextureDataModule implements PresentingStateModule<TextureDataPresenter>, TextureDataPresenter {
    public static final String NAME = "textureData";
    private final Map<String, CellActorFactory> actorFactoryMap = new HashMap<>();
    private final Actor defaultActor = null;

    /**
     * Привязать имя к фабрике актёров.
     */
    public void addActorFactory(String name, CellActorFactory cellActorFactory) {
        actorFactoryMap.put(name, cellActorFactory);
    }

    @Override
    public Actor createActor(String name) {
        CellActorFactory textureFactory = actorFactoryMap.get(name);
        if (textureFactory == null) return defaultActor;
        return textureFactory.create(new DrawingContext());
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
