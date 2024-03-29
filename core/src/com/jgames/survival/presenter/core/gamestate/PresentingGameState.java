package com.jgames.survival.presenter.core.gamestate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

@Bean
public class PresentingGameState {
    public static final String ALL_MODULES_CODE = "ALL MODULES";

    private final Map<String, PresentingStateModule<?>> modules = new HashMap<>();
    private final Map<Class<? extends PresentingStateModuleMutator>, PresentingStateModuleMutator> moduleMutators =
            new HashMap<>();

    public PresentingGameState(List<PresentingStateModule<?>> modules, List<PresentingStateModuleMutator> mutators) {
        modules.forEach(this::addStateModule);
        mutators.forEach(this::addModuleMutator);

        connectMutatorsWithModules();
    }

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
        moduleMutators.values().forEach(mutator -> {
            List<String> usedModuleNames = mutator.getUsedModuleNames();
            PresentingStateModule<?>[] usedModules = usedModuleNames.contains(ALL_MODULES_CODE)
                    ? modules.values().toArray(new PresentingStateModule[0])
                    : usedModuleNames.stream()
                        .map(modules::get)
                        .toArray(PresentingStateModule[]::new);

            mutator.connectWithModule(usedModules);
        });

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
