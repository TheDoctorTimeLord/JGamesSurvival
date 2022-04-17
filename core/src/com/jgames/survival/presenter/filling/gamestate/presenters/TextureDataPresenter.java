package com.jgames.survival.presenter.filling.gamestate.presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.ModulePresenter;

public interface TextureDataPresenter extends ModulePresenter {
    /**
     * Создать актёра по имени объекта.
     */
    Actor createActor(String name);
}
