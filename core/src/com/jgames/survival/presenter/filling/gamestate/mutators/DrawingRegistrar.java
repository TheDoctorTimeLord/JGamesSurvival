package com.jgames.survival.presenter.filling.gamestate.mutators;

import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;
import com.jgames.survival.utils.assets.TextureStorage;

import java.util.Collections;
import java.util.List;

/**
 * Регистрация модуля для хранения фабрик для имён объектов.
 */
public class DrawingRegistrar implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Collections.singletonList(DrawingModule.NAME);
    private DrawingModule drawingModule;

    /**
     * Присоединить CellActorFactory к DrawingModule.
     */
    public void connectCellActorFactory(String name, CellActorFactory cellActorFactory) {
        drawingModule.registrarCellActorFactory(name, cellActorFactory);
    }

    public TextureStorage getTextureStorage() {
        return drawingModule.getTextureStorage();
    }

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        drawingModule = (DrawingModule) modules[0];
    }
}