package com.jgames.survival.view.core.assets;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.jgames.survival.view.core.UIException;

public interface TexturePostLoader {
    void configureTextures(Map<String, Texture> foundedTextures, Map<String, Sprite> createdSprites) throws UIException;
}
