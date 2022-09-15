package com.jgames.survival.view.core.uielements.displaies;

import com.jgames.survival.view.core.CanBeActor;

public interface Display extends CanBeActor { //TODO можно верхний дисплей на основе com.badlogic.gdx.scenes.scene2d.uiTree
    void show();
    void hide();
}
