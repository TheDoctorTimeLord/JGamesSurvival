package com.jgames.survival.presenter.core.gamestate;

import java.util.List;

public interface PresentingStateModuleMutator {
    List<String> getUsedModuleNames();
    void connectWithModule(PresentingStateModule<?>... modules);
}
