package com.jgames.survival.view.core.displays;

import javax.annotation.Nullable;

import com.jgames.survival.view.core.Bindable;
import com.jgames.survival.view.core.CanBeActor;
import com.jgames.survival.view.core.HasName;

public interface Display extends CanBeActor, HasName, Bindable {
    @Nullable
    CanBeActor findNamedElement(String elementName);

    default void resize(int width, int height) {}

    default void show() { asActor().setVisible(true); }
    default void hide() { asActor().setVisible(false); }
}
