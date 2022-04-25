package com.jgames.survival.ui.cellactorfactories;

import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.utils.assets.SimpleTextureStorage;
import com.jgames.survival.utils.assets.TextureStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ActorFactoryConfig {
    public static Map<String, Function<TextureStorage, CellActorFactory>> ActorFactoriesMap = new HashMap<>() {{
        put("cell", textureStorage -> new TextureFactory(textureStorage.createSprite(SimpleTextureStorage.Constants.COMMON)));
    }};
}
