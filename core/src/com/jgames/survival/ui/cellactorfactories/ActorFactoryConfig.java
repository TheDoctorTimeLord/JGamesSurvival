package com.jgames.survival.ui.cellactorfactories;

import com.google.common.collect.ImmutableMap;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.utils.assets.TextureStorage;

import java.util.Map;
import java.util.function.Function;

/**
 * Простой конфиг для имен объектов и привязанным к ним фабрик актёров.
 */
public class ActorFactoryConfig {
    public static final Map<String, Function<TextureStorage, CellActorFactory>> ACTOR_FACTORIES_MAP = new
            ImmutableMap.Builder<String, Function<TextureStorage, CellActorFactory>>()
            .put("common", textureStorage -> new TextureFactory(textureStorage.createSprite(Constants.COMMON)))
            .put("tint", textureStorage -> new DeadObjectTint(textureStorage.createSprite(Constants.COMMON)))
            .build();
}
