package com.jgames.survival.presenter.filling.gamestate.modules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Модуль для хранения фабрик для имён объектов.
 */
public class DrawingModule implements PresentingStateModule<DrawingModulePresenter>, DrawingModulePresenter {
    public static final String NAME = "drawing";
    private final Map<String, CellActorFactory> cellActorFactoryMap = new HashMap<>();

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
    public Actor getActor(String objectTypeName, DrawingContext drawingContext) {
        return cellActorFactoryMap.get(objectTypeName).create(drawingContext);
    }
}