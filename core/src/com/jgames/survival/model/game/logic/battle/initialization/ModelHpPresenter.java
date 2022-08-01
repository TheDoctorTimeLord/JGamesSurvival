package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.utils.attributes.AttributeFindingUtils;

@BattleBeanPrototype
public class ModelHpPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(BattleContext battleContext) {
        List<BattleAction> result = new ArrayList<>();

        for (BattleModel battleModel : battleContext.getBattleState().getModelsInBattle()) {
            IntAttribute hp = AttributeFindingUtils.getHealthAttribute(battleModel);
            if (hp != null) {
                result.add(new ModelHpAction(battleModel.getId(), hp.getValue()));
            }
        }

        return result;
    }
}
