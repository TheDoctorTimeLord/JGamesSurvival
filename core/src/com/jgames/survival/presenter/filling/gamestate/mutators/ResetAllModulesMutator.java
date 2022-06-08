package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.ResettableModule;

@Bean
public class ResetAllModulesMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = List.of(PresentingGameState.ALL_MODULES_CODE);

    private final List<ResettableModule> resettableModules = new ArrayList<>();

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        for (PresentingStateModule<?> module : modules) {
            if (module instanceof ResettableModule resettableModule) {
                resettableModules.add(resettableModule);
            }
        }
    }

    public void resetAllModules() {
        for (ResettableModule resettableModule : resettableModules) {
            resettableModule.reset();
        }
    }
}
