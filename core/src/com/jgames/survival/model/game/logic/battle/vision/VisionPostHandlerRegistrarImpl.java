package com.jgames.survival.model.game.logic.battle.vision;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.events.BattleEvent;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.visible.UpdatableVisionInformationService;
import ru.jengine.battlemodule.standardfilling.visible.outside.VisionPostHandlerRegistrar;
import ru.jengine.eventqueue.event.PostHandler;

@BattleBeanPrototype
public class VisionPostHandlerRegistrarImpl implements VisionPostHandlerRegistrar {
    @Override
    public List<PostHandler<? extends BattleEvent>> getRegisteredPostHandlers(BattleState battleState,
            UpdatableVisionInformationService visionInformationService)
    {
        return List.of(new ChangeDirectionVisionUpdater(visionInformationService));
    }
}
