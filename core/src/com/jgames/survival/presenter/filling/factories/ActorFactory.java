package com.jgames.survival.presenter.filling.factories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

/**
 * Фабрика по производству актёров.
 */
public class ActorFactory {
    private final String actorName;

    public ActorFactory(String actorName) {
        this.actorName = actorName;
    }

    /**
     * Создать по метаданным объекта актёра.
     */
    public Actor create(DrawingContext drawingContext) {
        return new Actor();
    }

    public String getActorName() {
        return actorName;
    }
}
