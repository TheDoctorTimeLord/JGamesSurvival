package com.jgames.survival.view.core.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;

public interface TextureStorage extends Disposable {
    @Null Texture getTexture(String textureName);
    @Null Sprite createSprite(String textureName);
    @Null NinePatch createNinePatch(String patchName, int boardSize);
    @Null NinePatch createNinePatch(String patchName, int left, int right, int top, int bottom);
}
