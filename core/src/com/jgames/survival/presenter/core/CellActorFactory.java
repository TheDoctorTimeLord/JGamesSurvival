package com.jgames.survival.presenter.core;

import javax.annotation.Nullable;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.ui.UIException;

/**
 * Фабрика по производству актёров.
 */
public interface CellActorFactory {
    /**
     * Создать по метаданным объекта актёра.
     */
    Actor create(@Nullable GameObject gameObject) throws UIException;
}
