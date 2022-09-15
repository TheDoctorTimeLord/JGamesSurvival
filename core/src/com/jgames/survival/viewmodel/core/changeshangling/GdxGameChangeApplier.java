package com.jgames.survival.viewmodel.core.changeshangling;

import javax.annotation.Nullable;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.Gdx;

@Bean
public class GdxGameChangeApplier implements GameChangeApplier {
    @Override
    public void apply(@Nullable Runnable task) {
        if (task != null) {
            Gdx.app.postRunnable(task);
        }
    }
}
