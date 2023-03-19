package com.jgames.survival.ui.cellactorfactories;

import static com.jgames.survival.ui.assets.SimpleTextureStorage.Constants.DEAD_BODY;
import static com.jgames.survival.ui.constants.HardcodeObjectNames.*;
import static com.jgames.survival.ui.constants.SpecialObjectsNames.BACKGROUND;
import static com.jgames.survival.ui.constants.SpecialObjectsNames.TINT;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.ui.assets.TextureStorage;
import com.jgames.survival.ui.cellactorfactories.texturesfactory.PersonTextureSelector;
import com.jgames.survival.ui.cellactorfactories.texturesfactory.TexturesFactory;

/**
 * Простой конфиг для имен объектов и привязанным к ним фабрик актёров.
 */
public class ActorFactoryConfig {
    public static final Map<String, Function<TextureStorage, CellActorFactory>> SPECIAL_ACTOR_FACTORIES_MAP = new
            ImmutableMap.Builder<String, Function<TextureStorage, CellActorFactory>>()
            .put(BACKGROUND, textureStorage -> new TextureFactory(textureStorage.createSprite(Constants.CELL_BACKGROUND)))
            .put(TINT, textureStorage -> new DeadObjectTint(textureStorage.createSprite(Constants.COMMON)))
            .put(PERSON_HP, textureStorage -> new HpLabelFactory())
            .put(PERSON_TEAM, textureStorage -> new TeamFactory())
            .put(DEAD_BODY, textureStorage -> new TextureFactory(textureStorage.createSprite(DEAD_BODY)))
            .build();

    public static final Map<String, Function<TextureStorage, CellActorFactory>> ACTOR_FACTORIES_MAP = new
            ImmutableMap.Builder<String, Function<TextureStorage, CellActorFactory>>()
            .put(FIGHTER, textureStorage -> new TexturesFactory(
                    List.of(
                            textureStorage.createSprite(Constants.PERSON_UP),
                            textureStorage.createSprite(Constants.PERSON_RIGHT),
                            textureStorage.createSprite(Constants.PERSON_DOWN),
                            textureStorage.createSprite(Constants.PERSON_LEFT)
                    ),
                    new PersonTextureSelector()
            ))
            .put(ARCHER, textureStorage -> new TexturesFactory(
                    List.of(
                            textureStorage.createSprite(Constants.PERSON_UP),
                            textureStorage.createSprite(Constants.PERSON_RIGHT),
                            textureStorage.createSprite(Constants.PERSON_DOWN),
                            textureStorage.createSprite(Constants.PERSON_LEFT)
                    ),
                    new PersonTextureSelector()
            ))
            .put(WALL, textureStorage -> new TextureFactory(textureStorage.createSprite(Constants.WALL)))
            .build();
}
