package com.jgames.survival.presenter.filling.changeshandling;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.AvailableObjectTypeNames;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.DrawingRegistrar;
import com.jgames.survival.ui.cellactorfactories.ActorFactoryConfig;
import com.jgames.survival.ui.cellactorfactories.TextureFactory;
import com.jgames.survival.utils.assets.SimpleTextureStorage;
import com.jgames.survival.utils.assets.TextureStorage;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

import java.util.function.Function;

/**
 * Обрабатывает AvailableObjectTypeNames и регистрирует для них фабрики в DrawingModule.
 */
public class AvailableObjectTypeNameHandler implements GameChangeHandler {
    private final Function<TextureStorage, CellActorFactory> defaultTextureFactory = textureStorage ->
            new TextureFactory(textureStorage.createSprite(SimpleTextureStorage.Constants.COMMON));
    private TextureStorage textureStorage;
    private DrawingRegistrar drawingRegistrar;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        drawingRegistrar = presentingGameState.getModuleMutator(DrawingRegistrar.class);
        textureStorage = drawingRegistrar.getTextureStorage();
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof AvailableObjectTypeNames;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        AvailableObjectTypeNames availableObjectTypeNames = (AvailableObjectTypeNames) information;
        for (String name : availableObjectTypeNames.getAvailableTypeNames()) {
            CellActorFactory actorFactory = ActorFactoryConfig
                    .ACTOR_FACTORIES_MAP
                    .getOrDefault(name, defaultTextureFactory)
                    .apply(textureStorage);
            drawingRegistrar.connectCellActorFactory(name, actorFactory);
        }
    }
}
