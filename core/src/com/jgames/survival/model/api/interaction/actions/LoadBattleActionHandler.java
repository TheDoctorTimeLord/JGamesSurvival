package com.jgames.survival.model.api.interaction.actions;

import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.jsonconverter.JsonConverter;
import ru.jengine.jsonconverter.resources.ResourceMetadata;

import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.api.gameload.JsonApiConstants.ResourceNamespaces;
import com.jgames.survival.model.api.interaction.changes.initialize.BattleWasReloaded;
import com.jgames.survival.model.api.interaction.changes.initialize.RefreshUI;
import com.jgames.survival.model.game.logic.BattleStateBasedGenerator;

@Bean
public class LoadBattleActionHandler extends BaseActionHandler<LoadBattleAction> {
    private final JsonConverter jsonConverter;
    private final GameChangeSender gameChangeSender;

    public LoadBattleActionHandler(JsonConverter jsonConverter, GameChangeSender gameChangeSender) {
        this.jsonConverter = jsonConverter;
        this.gameChangeSender = gameChangeSender;
    }

    @Override
    public void handle(LoadBattleAction action) throws Exception {
        ResourceMetadata metadata = new ResourceMetadata(ResourceNamespaces.CORE_RESOURCES, "save");
        BattleState battleState = jsonConverter.convertFromJson(metadata, BattleState.class);

        gameChangeSender.sendGameChange(new BattleWasReloaded());
        gameBattleHandler.reloadGame(new BattleStateBasedGenerator(battleState));
        gameChangeSender.sendGameChange(new RefreshUI());
    }
}
