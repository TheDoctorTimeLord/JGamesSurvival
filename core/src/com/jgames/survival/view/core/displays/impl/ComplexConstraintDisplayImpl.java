package com.jgames.survival.view.core.displays.impl;

import java.util.Map.Entry;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.jgames.survival.utils.Multimap;
import com.jgames.survival.view.core.Bindable;
import com.jgames.survival.view.core.CanBeActor;
import com.jgames.survival.view.core.HasName;
import com.jgames.survival.view.core.displays.ComplexConstraintDisplay;
import com.jgames.survival.view.core.displays.Constraint;
import com.jgames.survival.view.core.displays.ConstraintManager;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.uielements.UIElement;

public class ComplexConstraintDisplayImpl extends BaseDisplay implements ComplexConstraintDisplay {
    private final Multimap<CanBeActor, Constraint> boundedElements = new Multimap<>();
    private final ConstraintManager constraintManager;
    private final WidgetGroup display;
    private final boolean isFillingScreen;

    public ComplexConstraintDisplayImpl(String displayName, ConstraintManager constraintManager, boolean isFillScreen) {
        super(displayName);
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
        bindComponent(display, withConstraint);
        display.show();
    }

    @Override
    public void unbindDisplay(Display display) {
        display.hide();
        unbindComponent(display);
    }

    @Override
    public void bindElement(UIElement element, String withConstraint) {
        bindComponent(element, withConstraint);
    }

    @Override
    public void unbindElement(UIElement element) {
        unbindComponent(element);
    }

    private <T extends CanBeActor & Bindable> void bindComponent(T component, String withConstraint) {
        Constraint constraint = constraintManager.getConstraint(withConstraint);

        Actor actor = component.asActor();
        this.display.addActor(actor);
        constraint.applyConstraint(actor, actor.getParent());

        boundedElements.add(component, constraint);

        component.onBind(getEventBus(), this);
    }

    private <T extends CanBeActor & Bindable> void unbindComponent(T component) {
        component.onUnbind(getEventBus());
        this.display.removeActor(component.asActor());
        boundedElements.removeKey(component);
    }

    @Override
    public CanBeActor findNamedElement(String elementName) {
        for (CanBeActor canBeActor : boundedElements.asMap().keySet()) {
            if (canBeActor instanceof HasName hasName && hasName.getName().equals(elementName)) {
                return canBeActor;
            }
        }
        return null;
    }

    @Override
    public void resize(int width, int height) {
        for (Entry<CanBeActor, Set<Constraint>> entry : boundedElements.asMap().entrySet()) {
            Actor actor = entry.getKey().asActor();
            Group parent = isFillingScreen ? actor.getStage().getRoot() : actor.getParent();

            for (Constraint constraint : entry.getValue()) {
                constraint.applyResizedConstraint(actor, parent, width, height);
            }
        }
    }

    @Override
    public ConstraintManager getConstraintManager() {
        return constraintManager;
    }
}
