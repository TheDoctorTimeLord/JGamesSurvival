package com.jgames.survival.model.game.logic.battle.models;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.models.BattleModelType;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.utils.AttributeUtils;

import com.jgames.survival.model.game.logic.battle.utils.LocationUtils;
import com.jgames.survival.model.game.logic.battle.utils.attributes.AttributeFindingUtils;

/**
 * Класс, описывающий бойца
 */
public class Fighter extends DynamicModel implements HasHealth, CanHit, CanMoveExtension {
    public Fighter(int id, BattleModelType type, AttributesContainer attributes) {
        super(id, type, attributes);
        setVision(true);
    }

    @Override
    public int getHealth() {
        IntAttribute healthAttribute = AttributeFindingUtils.getHealthAttribute(this);
        if (healthAttribute == null)
            return 0;
        return healthAttribute.getValue();
    }

    @Override
    public void damage(int damagePoints, @Nullable DispatcherBattleWrapper dispatcher) {
        IntAttribute healthAttribute = AttributeFindingUtils.getHealthAttribute(this);
        if (healthAttribute == null)
            return;
        healthAttribute.setValue(getHealth() - damagePoints);

        if (dispatcher != null) {
            AttributeUtils.notifyAboutChange(getId(), dispatcher, healthAttribute);
        }
    }

    @Override
    public boolean hasAvailablePosition(BattleState battleState) {
        return !getAvailablePosition(battleState).isEmpty();
    }

    @Override
    public Set<Point> getAvailablePosition(BattleState battleState) {
        Point modelPosition = getPosition();
        Direction modelDirection = getDirection();

        Point topOffset = modelDirection.getOffset();
        Point topPosition = checkPosition(modelPosition.add(topOffset), battleState);

        Point leftOffset = modelDirection.rotateLeft().getOffset();
        Point leftPosition = checkPosition(modelPosition.add(leftOffset), battleState);

        Point rightOffset = modelDirection.rotateRight().getOffset();
        Point rightPosition = checkPosition(modelPosition.add(rightOffset), battleState);

        Point topLeftPosition = topPosition != null && leftPosition != null
                ? checkPosition(modelPosition.add(leftOffset).add(topOffset), battleState)
                : null;

        Point topRightPosition = topPosition != null && rightPosition != null
                ? checkPosition(modelPosition.add(rightOffset).add(topOffset), battleState)
                : null;

        return Stream.of(topPosition, leftPosition, rightPosition, topLeftPosition, topRightPosition)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Nullable
    private static Point checkPosition(Point position, BattleState state) {
        return state.inBattlefieldBound(position) && state.getOnPosition(position).isEmpty() ? position : null;
    }

    @Override
    public boolean isDead() {
        return getHealth() <= 0;
    }

    @Override
    public boolean canHit(){
        return getMeleeDamagePoints() > 0;
    }

    @Override
    public int getMeleeDamagePoints() {
        IntAttribute meleeDamageAttribute = AttributeFindingUtils.getMeleeDamageAttribute(this);
        if (meleeDamageAttribute == null)
            return 0;
        return meleeDamageAttribute.getValue();
    }

    @Override
    public boolean hasOpponentsNearby(BattleState battleState) {
        List<BattleModel> enemies = getNearestBattleModels(battleState);
        return !enemies.isEmpty();
    }

    @Override
    public List<BattleModel> getNearestBattleModels(BattleState battleState) {
        Point modelPosition = getPosition();
        Direction modelDirection = getDirection();
        List<Point> pointNeighbour = LocationUtils.getNeighbours(modelPosition, battleState,
                LocationUtils.getThreeFrontOffsets(modelDirection));

        return pointNeighbour.stream()
                .map(battleState::getModelsOnPosition)
                .flatMap(Collection::stream)
                .filter(model -> model instanceof DynamicModel)
                .collect(Collectors.toList());
    }
}
