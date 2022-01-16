package com.jgames.survival.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;

public interface UIFactory {
    void prepareComponents(UIElements uiElements);
    Actor getFrontWidget();
    @Null Actor getActionableComponent();
}
