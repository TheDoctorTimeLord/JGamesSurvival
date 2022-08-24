package com.jgames.survival.presenter.filling.gamestate.presenters;

import javax.annotation.Nullable;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.core.model.GameObject;

/**
 * Presenter, возвращающий Actor по имени типа объекта.
 */
public interface DrawingModulePresenter extends ModulePresenter {
    Actor getActor(String factoryTypeName, @Nullable GameObject gameObject);
}
