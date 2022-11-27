package com.jgames.survival.view.core.displays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Null;

public interface Constraint {
    default void applyConstraint(Actor actor, Group parent) {
        applyResizedConstraint(actor, parent, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    void applyResizedConstraint(Actor actor, Group parent, int windowWidth, int windowHeight);
    @Null String getConstraintName();
}
