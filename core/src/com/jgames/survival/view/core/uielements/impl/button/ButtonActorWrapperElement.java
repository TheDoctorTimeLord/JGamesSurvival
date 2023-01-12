package com.jgames.survival.view.core.uielements.impl.button;

import org.jetbrains.annotations.Nullable;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jgames.survival.view.core.UIEventBus;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.events.UIComponentClicked;
import com.jgames.survival.view.core.events.UIComponentTypes;
import com.jgames.survival.view.core.uielements.impl.ActorWrapperElement;
import com.jgames.survival.view.core.utils.UIComponentsFinder;

public class ButtonActorWrapperElement extends ActorWrapperElement {
    private String[] buttonPath;
    private ChangeListener changeListener;

    public ButtonActorWrapperElement(Button button) {
        super(button);
    }

    @Override
    public void onBind(UIEventBus eventBus, @Nullable Display parent) {
        super.onBind(eventBus, parent);
        this.buttonPath = UIComponentsFinder.getComponentPath(this);
        asActor().addListener(changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                eventBus.registerEvent(new UIComponentClicked(buttonPath, UIComponentTypes.BUTTON, getName()));
            }
        });
    }

    @Override
    public void onUnbind(UIEventBus eventBus) {
        super.onUnbind(eventBus);
        this.buttonPath = null;
        asActor().removeListener(changeListener);
    }

    @Override
    public Button asActor() {
        return (Button)super.asActor();
    }

    @Override
    public String toString() {
        return "ButtonActorWrapperElement{" +
                "button=" + asActor() +
                '}';
    }
}
