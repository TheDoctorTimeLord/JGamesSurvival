package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.Collections;
import java.util.List;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.NameObjectResolvingModule;
import com.jgames.survival.presenter.filling.gamestate.resolvers.BackgroundResolver;
import com.jgames.survival.presenter.filling.gamestate.resolvers.ModelDataResolver;
import com.jgames.survival.presenter.filling.gamestate.resolvers.PersonResolver;
import com.jgames.survival.presenter.filling.gamestate.resolvers.TintResolver;
import com.jgames.survival.presenter.filling.gamestate.resolvers.WallResolver;

public class FakeNameObjectResolvingModuleMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Collections.singletonList(NameObjectResolvingModule.NAME);
    private static final List<ModelDataResolver> resolvers = List.of(
            new BackgroundResolver(),
            new PersonResolver(),
            new WallResolver(),
            new TintResolver()
    );

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        NameObjectResolvingModule module = (NameObjectResolvingModule)modules[0];
        resolvers.forEach(module::registerModelDataResolver);
    }
}
