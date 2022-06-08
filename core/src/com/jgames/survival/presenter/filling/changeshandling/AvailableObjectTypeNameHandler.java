package com.jgames.survival.presenter.filling.changeshandling;

import java.util.function.Function;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.initialize.AvailableObjectTypeNames;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.DrawingRegistrar;
import com.jgames.survival.ui.cellactorfactories.ActorFactoryConfig;
import com.jgames.survival.ui.cellactorfactories.TextureFactory;
import com.jgames.survival.ui.assets.SimpleTextureStorage;
import com.jgames.survival.ui.assets.TextureStorage;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

/**
 * Обрабатывает AvailableObjectTypeNames и регистрирует для них фабрики в DrawingModule.
 */
@Bean
public class AvailableObjectTypeNameHandler implements GameChangeHandler {
    private final Function<TextureStorage, CellActorFactory> defaultTextureFactory = textureStorage ->
            new TextureFactory(textureStorage.createSprite(SimpleTextureStorage.Constants.COMMON));
    private final TextureStorage textureStorage;
    private DrawingRegistrar drawingRegistrar;

    public AvailableObjectTypeNameHandler(TextureStorage textureStorage) {
        this.textureStorage = textureStorage;
    }

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        drawingRegistrar = presentingGameState.getModuleMutator(DrawingRegistrar.class);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof AvailableObjectTypeNames;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        AvailableObjectTypeNames availableObjectTypeNames = (AvailableObjectTypeNames) information;
        for (String name : availableObjectTypeNames.getAvailableTypeNames()) {
            CellActorFactory actorFactory = ActorFactoryConfig.ACTOR_FACTORIES_MAP
                    .getOrDefault(name, defaultTextureFactory)
                    .apply(textureStorage);
            drawingRegistrar.bindCellActorFactory(name, actorFactory);
        }
        
        ActorFactoryConfig.SPECIAL_ACTOR_FACTORIES_MAP.forEach((name, factory) ->
                drawingRegistrar.bindCellActorFactory(name, factory.apply(textureStorage)));
    }
}
