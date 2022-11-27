package com.jgames.survival.view.impl.displays;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public class BattlefieldDisplay implements Display {
    private final ScrollPane battlefieldActor;
    private final float baseScalePointToWindow;

    public BattlefieldDisplay(float scalePointToWindow) {
        this.baseScalePointToWindow = scalePointToWindow;

        WidgetGroup battlefieldContainer = new WidgetGroup();
        this.battlefieldActor = new ScrollPane(battlefieldContainer);
    }

    public void addBattlefieldComponent(UIElementWrapper element) {
        WidgetGroup battlefieldContainer = (WidgetGroup)battlefieldActor.getActor();
        battlefieldContainer.addActor(element.asActor());
    }

    @Override
    public Actor asActor() {
        return battlefieldActor;
    }
}
