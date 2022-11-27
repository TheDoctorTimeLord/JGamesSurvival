package com.jgames.survival.view.core.displays.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

public abstract class AlignConstraint extends FillParentConstraint {
    private final String constraintName;
    private final float signParentWidth;
    private final float signParentHeight;
    private final float signActorWidth;
    private final float signActorHeight;

    public AlignConstraint(String constraintName, float signParentWidth, float signParentHeight, float signActorWidth,
            float signActorHeight)
    {
        this.constraintName = constraintName;
        this.signParentWidth = signParentWidth;
        this.signParentHeight = signParentHeight;
        this.signActorWidth = signActorWidth;
        this.signActorHeight = signActorHeight;
    }

    @Override
    protected void applyConstraintInt(Actor actor, float parentWidth, float parentHeight) {
        Layout asLayout = (Layout)actor;
        float xPosition = (parentWidth / 2) * signParentWidth + (asLayout.getPrefWidth() / 2) * signActorWidth;
        float yPosition = (parentHeight / 2) * signParentHeight + (asLayout.getPrefHeight() / 2) * signActorHeight;

        actor.setPosition(xPosition, yPosition);
    }

    @Override
    public String getConstraintName() {
        return constraintName;
    }
}
