package com.jgames.survival.model.game.logic.battle.models;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModelType;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.battlemodule.standardfilling.visible.HasVision;
import ru.jengine.utils.AttributeUtils;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.utils.attributes.AttributeFindingUtils;

/**
 * Класс, описывающий динамическую сущность в игре
 */
public class Entity extends DynamicModel implements HasHealth, CanHit, HasVision {
    public Entity(int id, BattleModelType type, AttributesContainer attributes) {
        super(id, type, attributes);
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
    public int getVisionDistance() {
        IntAttribute attribute = getAttributes().getAttributeByPath(Attributes.VISION_DISTANCE);
        return attribute != null ? attribute.getValue() : 0;
    }

    @Override
    public boolean hasVision() {
        return getVisionDistance() > 0;
    }

    @Override
    public void setVisionDistance(int visionDistance) {
        IntAttribute attribute = getAttributes().getAttributeByPath(Attributes.VISION_DISTANCE);
        if (attribute != null) {
            attribute.setValue(visionDistance);
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

    @Override
    public int getMeleeDamagePoints() {
        IntAttribute meleeDamageAttribute = AttributeFindingUtils.getMeleeDamageAttribute(this);
        if (meleeDamageAttribute == null)
            return 0;
        return meleeDamageAttribute.getValue();
    }
}
