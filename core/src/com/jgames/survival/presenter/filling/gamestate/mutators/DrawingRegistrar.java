package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.Collections;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;

/**
 * Регистрация модуля для хранения фабрик для имён объектов.
 */
@Bean
public class DrawingRegistrar implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Collections.singletonList(DrawingModule.NAME);
    private DrawingModule drawingModule;

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        drawingModule = (DrawingModule) modules[0];
    }

    /**
     * Присоединить CellActorFactory к DrawingModule.
     */
    public void bindCellActorFactory(String name, CellActorFactory cellActorFactory) {
        drawingModule.registrarCellActorFactory(name, cellActorFactory);
    }

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }
}