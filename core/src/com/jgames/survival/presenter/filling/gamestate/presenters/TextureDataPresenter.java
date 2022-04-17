package com.jgames.survival.presenter.filling.gamestate.presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.modules.TextureData;
import com.jgames.survival.ui.UIFactory;

public interface TextureDataPresenter extends ModulePresenter {
    TextureData getTextureData(String name);

    Actor createActor(String name);
}
