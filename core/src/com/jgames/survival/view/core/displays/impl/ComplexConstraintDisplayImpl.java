package com.jgames.survival.view.core.displays.impl;

import java.util.Map.Entry;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.jgames.survival.utils.Multimap;
import com.jgames.survival.view.core.displays.ComplexConstraintDisplay;
import com.jgames.survival.view.core.displays.Constraint;
import com.jgames.survival.view.core.displays.ConstraintManager;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.uielements.UIElement;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public class ComplexConstraintDisplayImpl implements ComplexConstraintDisplay {
    private final Multimap<Constraint, Actor> boundedElements = new Multimap<>();
    private final ConstraintManager constraintManager;
    private final WidgetGroup display;
    private final boolean isFillingScreen;

    public ComplexConstraintDisplayImpl(ConstraintManager constraintManager, boolean isFillScreen) {
        this.constraintManager = constraintManager;
        this.display = new WidgetGroup();
        this.isFillingScreen = isFillScreen;

        this.display.setFillParent(isFillScreen);
    }

    @Override
    public WidgetGroup asActor() {
        return display;
    }

    @Override
    public void bindDisplay(Display display, String withConstraint) {
        Constraint constraint = constraintManager.getConstraint(withConstraint);

        Actor displayActor = display.asActor();
        this.display.addActor(displayActor);
        constraint.applyConstraint(displayActor, displayActor.getParent());

        boundedElements.add(constraint, displayActor);

        display.onBind();
        display.show();
    }

    @Override
    public void unbindDisplay(Display display) {
        Actor actor = display.asActor();

        display.hide();
        display.onUnbind();
        this.display.removeActor(actor);
        boundedElements.removeFromValues(actor);
    }

    @Override
    public void bindElement(UIElementWrapper elementWrapper, String withConstraint) {
        Constraint constraint = constraintManager.getConstraint(withConstraint);

        Actor elementActor = elementWrapper.asActor();
        this.display.addActor(elementActor);
        constraint.applyConstraint(elementActor, elementActor.getParent());

        boundedElements.add(constraint, elementActor);
    }

    @Override
    public void unbindElement(UIElement element) {
        Actor actor = element.asActor();

        this.display.removeActor(actor);
        boundedElements.removeFromValues(actor);
    }

    @Override
    public void resize(int width, int height) {
        for (Entry<Constraint, Set<Actor>> entry : boundedElements.asMap().entrySet()) {
            for (Actor actor : entry.getValue()) {
                Group parent = isFillingScreen ? actor.getStage().getRoot() : actor.getParent();
                entry.getKey().applyResizedConstraint(actor, parent, width, height);
            }
        }
    }

    @Override
    public ConstraintManager getConstraintManager() {
        return constraintManager;
    }
}
