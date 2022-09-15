package com.jgames.survival.viewmodel.core.changeshangling;

import javax.annotation.Nullable;

@FunctionalInterface
public interface GameChangeApplier {
    void apply(@Nullable Runnable task);
}
