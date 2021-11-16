package com.jgames.survival.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;

public interface UIComponent {
    void prepareComponent(UIElements uiElements);
    Actor getFrontWidget();
    @Null Actor getActionableComponent();
}
