package com.jgames.survival.model.game.logic.battle.vision;

import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.battlemodule.standardfilling.visible.UpdatableVisionInformationService;
import ru.jengine.eventqueue.event.PostHandler;

import com.jgames.survival.model.game.logic.battle.events.changedirection.ChangeDirectionEvent;

public class ChangeDirectionVisionUpdater implements PostHandler<ChangeDirectionEvent> {
    private final UpdatableVisionInformationService visionService;

    public ChangeDirectionVisionUpdater(UpdatableVisionInformationService visionService) {
        this.visionService = visionService;
    }

    @Override
    public Class<ChangeDirectionEvent> getHandlingEventType() {
        return ChangeDirectionEvent.class;
    }

    @Override
    public void handle(ChangeDirectionEvent event) {
        visionService.recalculateFieldOfView(event.getModelId());
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }
}
