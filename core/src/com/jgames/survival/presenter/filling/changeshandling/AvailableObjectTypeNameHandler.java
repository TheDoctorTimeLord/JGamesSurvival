package com.jgames.survival.presenter.filling.changeshandling;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.AvailableObjectTypeNames;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.DrawingRegistrar;
import com.jgames.survival.ui.cellactorfactories.ActorFactoryConfig;
import com.jgames.survival.utils.assets.TextureStorage;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

/**
 * Обрабатывает AvailableObjectTypeNames и регистрирует для них фабрики в DrawingModule.
 */
public class AvailableObjectTypeNameHandler implements GameChangeHandler {
    private DrawingRegistrar drawingRegistrar;
    private final TextureStorage textureStorage;

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
        availableObjectTypeNames.getAvailableTypeNames().forEach(name ->
                drawingRegistrar.registrarCellActorFactory(
                        name,
                        ActorFactoryConfig.ActorFactoriesMap.get(name).apply(textureStorage
                        )));
    }
}
