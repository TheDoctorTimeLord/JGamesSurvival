package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.AttributeUtils;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;

@BattleBeanPrototype
public class ModelHpPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(BattleContext battleContext) {
        List<BattleAction> result = new ArrayList<>();

        for (BattleModel battleModel : battleContext.getBattleState().getModelsInBattle()) {
            IntAttribute hp = AttributeUtils.extractInnerAttribute(
                    battleModel.getAttributes(),
                    List.of(Attributes.ATTRIBUTES),
                    Attributes.HIT_POINTS
            );
            if (hp != null) {
                result.add(new ModelHpAction(battleModel.getId(), hp.getValue()));
            }
        }

        return result;
    }
}
