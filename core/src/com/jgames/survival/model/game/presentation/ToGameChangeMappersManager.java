package com.jgames.survival.model.game.presentation;

import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.game.logic.GameBattleHandler;

@Bean
public class ToGameChangeMappersManager {
    private final List<ToGameChangeMapper> mappers;

    public ToGameChangeMappersManager(List<ToGameChangeMapper> mappers) {
        this.mappers = mappers;
    }

    public void configureMappers(GameChangeSender changeSender, GameBattleHandler battleHandler) {
        mappers.forEach(mapper -> mapper.configureMapper(changeSender, battleHandler));
    }
}
