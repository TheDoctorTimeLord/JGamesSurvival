package com.jgames.survival.view.core.displays;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;
import com.jgames.survival.view.core.CanBeActor;

public interface Display extends CanBeActor {
    default void onBind() { }
    default void onUnbind() { }
    @Null
    default Actor findNamedElement(String elementName) {
        return null;
    }

    default void resize(int width, int height) {}

    default void show() { asActor().setVisible(true); }
    default void hide() { asActor().setVisible(false); }
}
