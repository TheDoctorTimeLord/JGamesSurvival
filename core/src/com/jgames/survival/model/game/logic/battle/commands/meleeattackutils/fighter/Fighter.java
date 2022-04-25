package com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter;

import com.jgames.survival.model.game.logic.attributes.utils.GetAttributeUtils;
import com.jgames.survival.model.game.logic.battle.utils.constants.DirectionConstants;
import com.jgames.survival.model.game.logic.battle.utils.LocationUtils;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.models.BattleModelType;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.utils.AttributeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий бойца
 */
public class Fighter extends DynamicModel implements HasHealth, CanHit {
    DispatcherBattleWrapper dispatcher;

    public Fighter(int id, BattleModelType type, int maxHealth, DispatcherBattleWrapper dispatcher) {
        super(id, type);
        setHealth(maxHealth);
        setVision(true);
        this.dispatcher = dispatcher;
    }

    @Override
    public int getHealth() {
        IntAttribute healthAttribute = GetAttributeUtils.getHealthAttribute(this);
        if (healthAttribute == null)
            return 0;
        return healthAttribute.getValue();
    }

    @Override
    public void damage(int damagePoints) {
        int newHealthValue = getHealth() - damagePoints;
        setHealth(newHealthValue);
        if (newHealthValue <= 0) {
            AttributeUtils.notifyAboutRemove(getId(), dispatcher,
                    GetAttributeUtils.getHealthAttribute(this));
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
     * Назначение нового значения здоровья бойцу
     * @param newHealthValue новое значение здоровья бойца
     */
    private void setHealth(int newHealthValue) {
        IntAttribute healthAttribute = GetAttributeUtils.getHealthAttribute(this);
        if (healthAttribute == null)
            return;
        healthAttribute.setValue(newHealthValue);
        AttributeUtils.notifyAboutChange(getId(), dispatcher, healthAttribute);
    }

    /**
     * Проверяет наличие противников рядом с бойцом
     * @param battleContext контекст битвы
     * @return true, если рядом есть противники, иначе false
     */
    public boolean hasOpponentsNearby(BattleContext battleContext) {
        List<BattleModel> enemies = getNearestBattleModels(battleContext.getBattleState());
        return enemies.isEmpty();
    }

    /**
     * Возвращает персонажей, которые находятся рядом с бойцом
     * @param battleState состояние битвы
     */
    public List<BattleModel> getNearestBattleModels(BattleState battleState) {
        List<BattleModel> enemies = new ArrayList<>();
        Map<Point, List<Integer>> map = battleState.getMapFilling();
        Point modelPosition = getPosition();
        Direction modelDirection = getDirection();
        List<Point> pointNeighbour = LocationUtils.getNeighbour(
                modelPosition, battleState, DirectionConstants.offsets.get(modelDirection));
        for (Point neighbour : pointNeighbour) {
            List<Integer> pointContent = map.get(neighbour);
            if (pointContent.size()!= 0)
                enemies.addAll(battleState.resolveIds(pointContent));
        }
        return enemies;
    }

    /**
     * Возвращает значение, характеризующее силу удара,
     * с которой персонаж может нанести вред противнику
     */
    public int getMeleeDamagePoints() {
        IntAttribute meleeDamageAttribute = GetAttributeUtils.getMeleeDamageAttribute(this);
        if (meleeDamageAttribute == null)
            return 0;
        return meleeDamageAttribute.getValue();
    }
}
