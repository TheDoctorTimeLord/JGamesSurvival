package com.jgames.survival.view.core;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface CanBeActor {
    /**
     * Возвращает объект в виде {@link Actor}. Реализация обязана на все вызовы данного метода у объекта возвращать
     * всегда один и тот же {@link Actor}
     */
    Actor asActor();
}
