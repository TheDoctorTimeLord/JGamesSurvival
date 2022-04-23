package com.jgames.survival.presenter.core;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

/**
 * Фабрика по производству актёров.
 */
public interface CellActorFactory {
    /**
     * Создать по метаданным объекта актёра.
     */
    Actor create(DrawingContext drawingContext);
}
