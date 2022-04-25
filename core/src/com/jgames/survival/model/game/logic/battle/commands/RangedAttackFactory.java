package com.jgames.survival.model.game.logic.battle.commands;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyPartsConstants.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyPartsConstants.LEFT_ARM;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyPartsConstants.RIGHT_ARM;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.StateConstants.DAMAGED;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.StateConstants.STATE;
import static com.jgames.survival.model.game.logic.battle.vision.VisionScopeConstants.VISIBLE;
import static ru.jengine.utils.AttributeUtils.extractInnerAttribute;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.battlemodule.standardfilling.visible.VisionInformationService;
import ru.jengine.beancontainer.annotations.Bean;

@Bean
public class RangedAttackFactory implements BattleCommandFactory<RangedAttackParameters, RangedAttack> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return true;
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
        StringAttribute leftArmState = extractInnerAttribute(attributes, List.of(BODY_PARTS, LEFT_ARM), STATE);
        StringAttribute rightArmStata = extractInnerAttribute(attributes, List.of(BODY_PARTS, RIGHT_ARM), STATE);

        return leftArmState != null && rightArmStata != null &&
                !(DAMAGED.equals(leftArmState.getValue()) && DAMAGED.equals(rightArmStata.getValue()));
    }
}
