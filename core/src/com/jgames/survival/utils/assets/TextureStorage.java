package com.jgames.survival.utils.assets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public interface TextureStorage extends Disposable {
    TextureStorage load(TextureStorageConfiguration configuration);
    Sprite createSprite(String spriteName);
    NinePatch createNinePatch(String patchName);
}
