package com.jgames.survival.view.core.displays.impl;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jgames.survival.view.core.CanBeActor;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.UIEventBus;

public class RootDisplay extends BaseDisplay {
    public static final String NAME = "#rootDisplay";

    private final List<Display> boundedDisplays = new ArrayList<>();
    private final Stage stage;

    public RootDisplay(Stage stage, UIEventBus eventBus) {
        super(NAME);
        this.stage = stage;

        onBind(eventBus, null);
    }

    @Override
    public Actor asActor() {
        return stage.getRoot();
    }

    public CanBeActor findNamedElement(String elementName) {
        for (Display boundedDisplay : boundedDisplays) {
            if (boundedDisplay.getName().equals(elementName)) {
                return boundedDisplay;
            }
        }
        return null;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        for (Display display : boundedDisplays) {
            display.resize(width, height);
        }
    }

    public void bindDisplay(Display display) {
        stage.addActor(display.asActor());
        boundedDisplays.add(display);
        display.onBind(getEventBus(), this);
        display.show();
    }

    public void unbindDisplay(Display display) {
        display.hide();
        display.onUnbind(getEventBus());
        stage.getRoot().removeActor(display.asActor());
        boundedDisplays.remove(display);
    }
}
