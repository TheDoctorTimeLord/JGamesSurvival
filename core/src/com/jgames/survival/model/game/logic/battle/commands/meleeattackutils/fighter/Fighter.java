package com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.LocationUtils;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий бойца
 */
public class Fighter extends DynamicModel implements HasHealth, CanHit {
    private int health;
    private static final Point[] ENEMY_OFFSETS = new Point[] {
            PointPool.obtain(-1, 1), PointPool.obtain(0, 1), PointPool.obtain(1, 1)};


    public Fighter(int id, int maxHealth) {
        super(id);
        this.health = maxHealth;
        setVision(true);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void damage(int damagePoints) {
        int health = getHealth();
        this.health = health - damagePoints;
    }

    @Override
    public boolean isDead() {
        return getHealth() <= 0;
    }

    @Override
    public boolean canHit(){
        String leftArmState = this
                .getAttributes()
                .getAsContainer(AttributesConstants.BodyPartsConstants.BODY_PARTS)
                .getAsContainer(AttributesConstants.BodyPartsConstants.LEFT_ARM)
                .getAsString(StateConstants.STATE).getValue();
        String rightArmState = this
                .getAttributes()
                .getAsContainer(AttributesConstants.BodyPartsConstants.BODY_PARTS)
                .getAsContainer(AttributesConstants.BodyPartsConstants.RIGHT_ARM)
                .getAsString(StateConstants.STATE).getValue();
        return StateConstants.DESTROYED.equals(leftArmState) && StateConstants.DESTROYED.equals(rightArmState);
    }

    /**
     * Получение позиции, на которой находиться боец
     */
    public Point getFighterPosition() {
        return getPosition();
    }

    /**
     * Проверяет наличие противников рядом с бойцом
     * @param battleContext контекст битвы
     * @return true, если рядом есть противники, иначе false
     */
    public boolean haveOpponentsNearby(BattleContext battleContext) {
        List<BattleModel> enemies = getNearestBattleModels(battleContext);
        return enemies.isEmpty();
    }

    /**
     * Возвращает персонажей, которые находятся рядом с бойцом
     * @param battleContext контекст битвы
     */
    public List<BattleModel> getNearestBattleModels(BattleContext battleContext) {
        List<BattleModel> enemies = new ArrayList<>();
        Map<Point, List<Integer>> map = battleContext.getBattleState().getMapFilling();
        Point modelPosition = getFighterPosition();
        List<Point> pointNeighbour = LocationUtils
                .getNeighbour(modelPosition, battleContext.getBattleState(), ENEMY_OFFSETS);
        for (Point neighbour : pointNeighbour) {
            List<Integer> pointContent = map.get(neighbour);
            if (pointContent.size()!= 0)
                enemies.addAll(battleContext.getBattleState().resolveIds(pointContent));
        }
        return enemies;
    }

    /**
     * Получение силы удара, с которой персонаж может нанести вред противнику
     */
    public int getMeleeDamagePoints() {
       return this.getAttributes()
                .getAsContainer(AttributesConstants.Attributes.ATTRIBUTES)
                .getAsInt(AttributesConstants.Attributes.MELEE_DAMAGE_POINTS).getValue();

    }
}
