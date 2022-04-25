package com.jgames.survival.presenter.filling.gamestate.presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

/**
 * Presenter, возвращающий Actor по имени типа объекта.
 */
public interface DrawingModulePresenter extends ModulePresenter {
    Actor getActor(String objectTypeName, DrawingContext drawingContext);
}
