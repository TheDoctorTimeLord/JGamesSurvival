package com.jgames.survival.view.core.assets.configuration;

import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.jgames.survival.view.core.assets.TexturePostLoader;

@Bean
public class PersonLeftTexturePostLoader implements TexturePostLoader {
    public static final String PERSON_LEFT = "personLeft";
    public static final String PERSON_RIGHT = "personRight";

    @Override
    public void configureTextures(Map<String, Texture> foundedTextures, Map<String, Sprite> createdSprites) {
        Sprite personRight = new Sprite(foundedTextures.get(PERSON_RIGHT));
        personRight.flip(true, false);
        createdSprites.put(PERSON_LEFT, personRight);
    }
}
