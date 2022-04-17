package com.jgames.survival.presenter.filling.gamestate.presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.modules.BoundedTextureData;

public interface TextureDataPresenter extends ModulePresenter {
    BoundedTextureData getTextureData(String name);

    Actor createActor(String name);
}
