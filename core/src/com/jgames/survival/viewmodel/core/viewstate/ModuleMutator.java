package com.jgames.survival.viewmodel.core.viewstate;

import java.util.List;

public interface ModuleMutator {
    List<String> getUsedModuleNames();
    void connectWithModule(ViewStateModule<?>... modules);
}
