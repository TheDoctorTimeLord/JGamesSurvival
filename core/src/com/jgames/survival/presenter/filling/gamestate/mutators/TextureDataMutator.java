package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.Collections;
import java.util.List;

import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.TextureDataModule;

/**
 * Мутатор для модуля TextureDataModule.
 */
public class TextureDataMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Collections.singletonList(TextureDataModule.NAME);

    private TextureDataModule textureDataModule;

    /**
     * Присоединить фабрику актёров к TextureDataModule.
     */
    public void connectActorFactory(String name, CellActorFactory cellActorFactory) {
        textureDataModule.addActorFactory(name, cellActorFactory);
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
