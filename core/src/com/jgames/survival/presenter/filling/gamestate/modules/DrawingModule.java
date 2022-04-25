package com.jgames.survival.presenter.filling.gamestate.modules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.ui.cellactorfactories.TextureFactory;
import com.jgames.survival.ui.widgets.TextListWidget;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.utils.assets.TextureStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * Модуль для хранения фабрик для имён объектов.
 */
public class DrawingModule implements PresentingStateModule<DrawingModulePresenter>, DrawingModulePresenter {
    public static final String NAME = "drawing";
    private final Map<String, CellActorFactory> cellActorFactoryMap = new HashMap<>();
    private final Actor defaultActor;

    private final TextureStorage textureStorage;

    public DrawingModule(TextureStorage textureStorage) {
        this.textureStorage = textureStorage;
        defaultActor = new TextureFactory(textureStorage.createSprite(Constants.COMMON)).create(new DrawingContext());
    }

    /**
     * Регистрация CellActorFactory.
     */
    public void registrarCellActorFactory(String name, CellActorFactory cellActorFactory) {
        cellActorFactoryMap.put(name, cellActorFactory);
    }

    public TextureStorage getTextureStorage() {
        return textureStorage;
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
    public Actor getActor(String objectTypeName, DrawingContext drawingContext) {
        return !cellActorFactoryMap.containsKey(objectTypeName)
                ? defaultActor
                : cellActorFactoryMap.get(objectTypeName).create(drawingContext);
    }
}