package com.jgames.survival.viewmodel.core.viewstate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

@Bean
public class PresentingViewState {
    public static final String ALL_MODULES_CODE = "ALL MODULES";

    private final Map<String, ViewStateModule<?>> modules = new HashMap<>();
    private final Map<Class<? extends ModuleMutator>, ModuleMutator> moduleMutators =
            new HashMap<>();

    public PresentingViewState(List<ViewStateModule<?>> modules, List<ModuleMutator> mutators) {
        modules.forEach(this::addStateModule);
        mutators.forEach(this::addModuleMutator);

        connectMutatorsWithModules();
    }

    public PresentingViewState addStateModule(
            ViewStateModule<?> module) {
        modules.put(module.getModuleName(), module);
        return this;
    }

    public PresentingViewState addModuleMutator(ModuleMutator mutator) {
        return addModuleMutator(mutator.getClass(), mutator);
    }

    public PresentingViewState addModuleMutator(Class<? extends ModuleMutator> mutatorClass,
            ModuleMutator mutator) {
        moduleMutators.put(mutatorClass, mutator);
        return this;
    }

    public PresentingViewState connectMutatorsWithModules() {
        moduleMutators.values().forEach(mutator -> {
            List<String> usedModuleNames = mutator.getUsedModuleNames();
            ViewStateModule<?>[] usedModules = usedModuleNames.contains(ALL_MODULES_CODE)
                    ? modules.values().toArray(new ViewStateModule[0])
                    : usedModuleNames.stream()
                        .map(modules::get)
                        .toArray(ViewStateModule[]::new);

            mutator.connectWithModule(usedModules);
        });

        return this;
    }

    @SuppressWarnings("unchecked")
    public <P extends ModulePresenter> P getModulePresenter(String moduleName) {
        return (P)modules.get(moduleName);
    }

    @SuppressWarnings("unchecked")
    public <M extends ModuleMutator> M getModuleMutator(Class<M> mutatorClass) {
        return (M)moduleMutators.get(mutatorClass);
    }
}
