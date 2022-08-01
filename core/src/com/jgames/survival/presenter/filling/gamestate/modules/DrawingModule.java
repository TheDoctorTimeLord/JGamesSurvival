package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.ui.cellactorfactories.TextureFactory;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.ui.assets.TextureStorage;

/**
 * Модуль для хранения фабрик для имён объектов.
 */
@Bean
public class DrawingModule implements PresentingStateModule<DrawingModulePresenter>, DrawingModulePresenter, ResettableModule {
    public static final String NAME = "drawing";
    private final Map<String, CellActorFactory> cellActorFactoryMap = new HashMap<>();
    private final CellActorFactory defaultFactory;

    public DrawingModule(TextureStorage textureStorage) {
        defaultFactory = new TextureFactory(textureStorage.createSprite(Constants.COMMON));
    }

    /**
     * Регистрация CellActorFactory.
     */
    public void registrarCellActorFactory(String name, CellActorFactory cellActorFactory) {
        cellActorFactoryMap.put(name, cellActorFactory);
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public DrawingModulePresenter getPresenter() {
        return this;
    }

    @Override
    public Actor getActor(String factoryTypeName, @Nullable GameObject gameObject) {
        CellActorFactory cellActorFactory = cellActorFactoryMap.getOrDefault(factoryTypeName, defaultFactory);
        try {
            return cellActorFactory.create(gameObject);
        } catch (Exception e) {
            Gdx.app.error("TEXTURE_DRAWING", "Error for type [%s] in factory [%s]".formatted(gameObject, cellActorFactory), e);
            return defaultFactory.create(gameObject);
        }
    }

    @Override
    public void reset() {
        cellActorFactoryMap.clear();
    }
}