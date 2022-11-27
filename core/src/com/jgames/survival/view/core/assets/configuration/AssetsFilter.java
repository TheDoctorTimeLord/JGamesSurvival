package com.jgames.survival.view.core.assets.configuration;

import java.util.Set;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.files.FileHandle;
import com.jgames.survival.view.core.assets.TextureFilter;

@Bean
public class AssetsFilter implements TextureFilter {
    private static final Set<String> ASSET_EXTENSIONS = Set.of("png", "PNG");

    @Override
    public boolean needLoadTexture(FileHandle resourceHandler) {
        return ASSET_EXTENSIONS.contains(resourceHandler.extension());
    }
}
