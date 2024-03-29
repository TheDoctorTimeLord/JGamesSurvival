package com.jgames.survival.model.game.logic.battle.commands.rangedattack;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.LEFT_ARM;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.RIGHT_ARM;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.StateValue.DAMAGED;
import static com.jgames.survival.model.game.logic.battle.vision.VisionScopeConstants.VISIBLE;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.battlemodule.standardfilling.visible.HasVision;
import ru.jengine.battlemodule.standardfilling.visible.VisionInformationService;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;

@Bean
public class RangedAttackFactory implements BattleCommandFactory<SelectionFromSetParameters<BattleModel>, RangedAttack> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return model instanceof HasVision;
    }

    @Override
    public boolean isAvailableCommand(BattleModel model, BattleContext battleContext) {
        return canRangedAttack(model) && !getVisibleEnemies(model, battleContext).isEmpty();
    }

    @Override
    public RangedAttack createBattleCommand(BattleModel model, BattleContext battleContext) {
        return new RangedAttack(getVisibleEnemies(model, battleContext));
    }

    public static Set<BattleModel> getVisibleEnemies(BattleModel model, BattleContext battleContext) {
        BattleState battleState = battleContext.getBattleState();
        VisionInformationService visionInformationService = battleContext.getInformationCenter()
                .getService(VisionInformationService.class);
        Set<Point> visiblePoints = visionInformationService.getVisiblePoints(model.getId(), VISIBLE);
        return visiblePoints.stream()
                .map(battleState::getModelsOnPosition)
                .filter(models -> !models.isEmpty())
                .flatMap(Collection::stream)
                .filter(battleModel -> !model.equals(battleModel))
                .filter(battleModel -> battleModel instanceof DynamicModel)
                .collect(Collectors.toSet());
    }

    public static boolean canRangedAttack(BattleModel model) {
        AttributesContainer attributes = model.getAttributes();
        IntAttribute leftArmState = attributes.getAttributeByPath(BODY_PARTS, LEFT_ARM, STATE);
        IntAttribute rightArmStata = attributes.getAttributeByPath(BODY_PARTS, RIGHT_ARM, STATE);

        return leftArmState != null && rightArmStata != null &&
                (DAMAGED.isGreater(leftArmState.getValue()) || DAMAGED.isGreater(rightArmStata.getValue()));
    }
}
