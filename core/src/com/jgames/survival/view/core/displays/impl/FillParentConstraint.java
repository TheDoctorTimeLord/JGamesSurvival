package com.jgames.survival.view.core.displays.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.jgames.survival.view.core.UIRuntimeException;
import com.jgames.survival.view.core.displays.Constraint;

public abstract class FillParentConstraint implements Constraint {
    @Override
    public void applyResizedConstraint(Actor actor, Group parent, int windowWidth, int windowHeight) {
        if (parent == null) {
            throw new UIRuntimeException("Constraint was applied to actor [%s] without parent".formatted(actor));
        }

        if (actor instanceof Widget widget) {
            widget.setFillParent(true);
        } else if (actor instanceof WidgetGroup widgetGroup) {
            widgetGroup.setFillParent(true);
        } else  {
            throw new UIRuntimeException("Constraint is not available for " + actor.toString());
        }

        Stage stage = actor.getStage();
        float parentWidth;
        float parentHeight;

        if (stage != null && parent == stage.getRoot()) {
            parentWidth = stage.getWidth();
            parentHeight = stage.getHeight();
        } else {
            parentWidth = parent.getWidth();
            parentHeight = parent.getHeight();
        }

        applyConstraintInt(actor, parentWidth, parentHeight);
    }

    protected abstract void applyConstraintInt(Actor actor, float parentWidth, float parentHeight);
}
