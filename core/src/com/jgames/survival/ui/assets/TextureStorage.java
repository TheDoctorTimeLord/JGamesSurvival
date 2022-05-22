package com.jgames.survival.ui.assets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public interface TextureStorage extends Disposable {
    Sprite createSprite(String spriteName);
    NinePatch createNinePatch(String patchName);
}
