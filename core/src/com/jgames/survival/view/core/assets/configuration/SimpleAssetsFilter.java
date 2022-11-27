package com.jgames.survival.view.core.assets.configuration;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.files.FileHandle;
import com.jgames.survival.view.core.assets.TextureFilter;

@Bean
public class SimpleAssetsFilter implements TextureFilter {
    private static final String SIMPLE_ASSETS_PREFIX = "simple";

    @Override
    public boolean needLoadTexture(FileHandle resourceHandler) {
        return !resourceHandler.path().startsWith(SIMPLE_ASSETS_PREFIX);
    }
}
