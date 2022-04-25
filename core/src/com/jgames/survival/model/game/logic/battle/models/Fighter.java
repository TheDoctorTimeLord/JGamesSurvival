package com.jgames.survival.model.game.logic.battle.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.BattleContext;
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

import com.jgames.survival.model.game.logic.attributes.utils.GetAttributeUtils;
import com.jgames.survival.model.game.logic.battle.utils.LocationUtils;

/**
 * Класс, описывающий бойца
 */
public class Fighter extends DynamicModel implements HasHealth, CanHit {
    public Fighter(int id, BattleModelType type, AttributesContainer attributes) {
        super(id, type, attributes);
        setVision(true);
    }

    @Override
    public int getHealth() {
        IntAttribute healthAttribute = GetAttributeUtils.getHealthAttribute(this);
        if (healthAttribute == null)
            return 0;
        return healthAttribute.getValue();
    }

    @Override
    public void damage(int damagePoints, @Nullable DispatcherBattleWrapper dispatcher) {
        IntAttribute healthAttribute = GetAttributeUtils.getHealthAttribute(this);
        if (healthAttribute == null)
            return;
        healthAttribute.setValue(getHealth() - damagePoints);

        if (dispatcher != null) {
            AttributeUtils.notifyAboutChange(getId(), dispatcher, healthAttribute);
        }
    }

    @Override
    public boolean isDead() {
        return getHealth() <= 0;
    }

    @Override
    public boolean canHit(){
        return getMeleeDamagePoints() > 0;
    }

    /**
     * Проверяет наличие противников рядом с бойцом
     * @param battleContext контекст битвы
     * @return true, если рядом есть противники, иначе false
     */
    @Override
    public boolean hasOpponentsNearby(BattleContext battleContext) {
        List<BattleModel> enemies = getNearestBattleModels(battleContext.getBattleState());
        return enemies.isEmpty();
    }

    /**
     * Возвращает персонажей, которые находятся рядом с бойцом
     * @param battleState состояние битвы
     */
    @Override
    public List<BattleModel> getNearestBattleModels(BattleState battleState) {
        Point modelPosition = getPosition();
        Direction modelDirection = getDirection();
        List<Point> pointNeighbour = LocationUtils.getNeighbours( modelPosition, battleState,
                LocationUtils.getThreeFrontOffsets(modelDirection));

        return pointNeighbour.stream()
                .map(battleState::getModelsOnPosition)
                .flatMap(Collection::stream)
                .filter(model -> model instanceof DynamicModel)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает значение, характеризующее силу удара,
     * с которой персонаж может нанести вред противнику
     */
    @Override
    public int getMeleeDamagePoints() {
        IntAttribute meleeDamageAttribute = GetAttributeUtils.getMeleeDamageAttribute(this);
        if (meleeDamageAttribute == null)
            return 0;
        return meleeDamageAttribute.getValue();
    }
}
