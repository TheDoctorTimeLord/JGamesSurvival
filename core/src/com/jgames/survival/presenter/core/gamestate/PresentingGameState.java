package com.jgames.survival.presenter.core.gamestate;

import java.util.HashMap;
import java.util.Map;

public class PresentingGameState {
    private final Map<String, PresentingStateModule<?>> modules = new HashMap<>();
    private final Map<Class<? extends PresentingStateModuleMutator>, PresentingStateModuleMutator> moduleMutators =
            new HashMap<>();

    public PresentingGameState addStateModule(PresentingStateModule<?> module) {
        modules.put(module.getModuleName(), module);
        return this;
    }

    public PresentingGameState addModuleMutator(PresentingStateModuleMutator mutator) {
        return addModuleMutator(mutator.getClass(), mutator);
    }

    public PresentingGameState addModuleMutator(Class<? extends PresentingStateModuleMutator> mutatorClass,
            PresentingStateModuleMutator mutator) {
        moduleMutators.put(mutatorClass, mutator);
        return this;
    }

    public PresentingGameState connectMutatorsWithModules() {
        moduleMutators.values().forEach(mutator ->
                mutator.connectWithModule(
                        mutator.getUsedModuleNames().stream()
                                .map(modules::get)
                                .toArray(PresentingStateModule[]::new)
                )
        );

        return this;
    }

    @SuppressWarnings("unchecked")
    public <P extends ModulePresenter> P getModulePresenter(String moduleName) {
        return (P)modules.get(moduleName);
    }

    @SuppressWarnings("unchecked")
    public <M extends PresentingStateModuleMutator> M getModuleMutator(Class<M> mutatorClass) {
        return (M)moduleMutators.get(mutatorClass);
    }
}
