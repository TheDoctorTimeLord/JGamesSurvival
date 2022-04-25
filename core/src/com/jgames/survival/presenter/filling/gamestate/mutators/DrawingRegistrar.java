package com.jgames.survival.presenter.filling.gamestate.mutators;

import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;

import java.util.Collections;
import java.util.List;

/**
 * Регистрация модуля для хранения фабрик для имён объектов.
 */
public class DrawingRegistrar implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Collections.singletonList(DrawingModule.NAME);
    private DrawingModule drawingModule;

    /**
     * Регистрация CellActorFactory.
     */
    public void registrarCellActorFactory(String name, CellActorFactory cellActorFactory) {
        drawingModule.registrarCellActorFactory(name, cellActorFactory);
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