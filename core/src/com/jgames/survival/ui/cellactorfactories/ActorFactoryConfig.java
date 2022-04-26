package com.jgames.survival.ui.cellactorfactories;

import static com.jgames.survival.ui.constants.HardcodeObjectNames.PERSON;
import static com.jgames.survival.ui.constants.HardcodeObjectNames.PERSON_HP;
import static com.jgames.survival.ui.constants.HardcodeObjectNames.WALL;
import static com.jgames.survival.ui.constants.SpecialObjectsNames.BACKGROUND;
import static com.jgames.survival.ui.constants.SpecialObjectsNames.TINT;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.badlogic.gdx.utils.Align;
import com.google.common.collect.ImmutableMap;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.utils.assets.TextureStorage;

/**
 * Простой конфиг для имен объектов и привязанным к ним фабрик актёров.
 */
public class ActorFactoryConfig {
    public static final Map<String, Function<TextureStorage, CellActorFactory>> ACTOR_FACTORIES_MAP = new
            ImmutableMap.Builder<String, Function<TextureStorage, CellActorFactory>>()
            .put(PERSON, textureStorage -> new TexturesFactory(
                    List.of(
                            textureStorage.createSprite(Constants.PERSON_UP),
                            textureStorage.createSprite(Constants.PERSON_RIGHT),
                            textureStorage.createSprite(Constants.PERSON_DOWN),
                            textureStorage.createSprite(Constants.PERSON_LEFT)
                    ),
                    (textures, context) -> textures.get(context.getModelData().getDirection().ordinal())
            ))
            .put(WALL, textureStorage -> new AlignedLabelFactory("W", Align.center))
            .build();

    public static final Map<String, Function<TextureStorage, CellActorFactory>> SPECIAL_ACTOR_FACTORIES_MAP = new
            ImmutableMap.Builder<String, Function<TextureStorage, CellActorFactory>>()
            .put(BACKGROUND, textureStorage -> new TextureFactory(textureStorage.createSprite(Constants.COMMON)))
            .put(TINT, textureStorage -> new DeadObjectTint(textureStorage.createSprite(Constants.COMMON)))
            .put(PERSON_HP, textureStorage -> new HpLabelFactory())
            .build();
}
