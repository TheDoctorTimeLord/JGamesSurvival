package com.jgames.survival.presenter.filling.gamestate.mutators;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.BoundedTextureData;
import com.jgames.survival.presenter.filling.gamestate.modules.TextureDataModule;
import com.jgames.survival.presenter.filling.factories.ActorFactory;

import java.util.Collections;
import java.util.List;

/**
 * Мутатор для модуля TextureDataModule.
 */
public class TextureDataMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Collections.singletonList(TextureDataModule.NAME);

    private TextureDataModule textureDataModule;

    /**
     * Присоединить метаданные о текстуре к TextureDataModule.
     */
    public void connectBoundingTextureData(BoundedTextureData boundedTextureData) {
        textureDataModule.addBoundingTextureData(boundedTextureData);
    }

    /**
     * Присоединить фабрику актёров к TextureDataModule.
     */
    public void connectActorFactory(ActorFactory actorFactory) {
        textureDataModule.addActorFactory(actorFactory);
    }

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        textureDataModule = (TextureDataModule) modules[0];
    }
}
