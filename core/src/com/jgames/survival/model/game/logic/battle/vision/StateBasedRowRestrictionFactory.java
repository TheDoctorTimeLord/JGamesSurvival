package com.jgames.survival.model.game.logic.battle.vision;

import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.visible.HasVision;
import ru.jengine.battlemodule.standardfilling.visible.outside.CustomRowRestriction;
import ru.jengine.battlemodule.standardfilling.visible.outside.CustomRowRestrictionFactory;
import ru.jengine.beancontainer.annotations.Bean;

@Bean
public class StateBasedRowRestrictionFactory implements CustomRowRestrictionFactory {
    @Override
    public CustomRowRestriction createRowRestriction(HasVision hasVision, BattleState battleState) {
        StateBasedRowRestriction rowRestriction = new StateBasedRowRestriction();
        rowRestriction.initialize(hasVision, battleState);
        return rowRestriction;
    }
}
