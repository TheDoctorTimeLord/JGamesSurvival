package com.jgames.survival.view.core.uielements.impl;

import javax.annotation.Nullable;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.view.core.UIEventBus;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.uielements.UIElement;

public class ActorWrapperElement implements UIElement {
    private final Actor actor;
    private Display parent;

    public ActorWrapperElement(Actor actor) {
        this.actor = actor;
    }

    @Override
    public Actor asActor() {
        return actor;
    }

    @Override
    public String getName() {
        return actor.getName();
    }

    @Override
    public void onBind(UIEventBus eventBus, @Nullable Display parent) {
        this.parent = parent;
    }

    @Override
    public void onUnbind(UIEventBus eventBus) {
        this.parent = null;
    }

    @Nullable
    @Override
    public Display getParent() {
        return parent;
    }
}
