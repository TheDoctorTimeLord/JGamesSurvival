package com.jgames.survival.presenter.filling.factories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

/**
 * Фабрика по производству актёров.
 */
public interface ActorFactory {
    /**
     * Создать по метаданным объекта актёра.
     */
    Actor create(DrawingContext drawingContext);
}
