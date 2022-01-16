package com.jgames.survival.model.api.changes;

import com.jgames.survival.model.api.GameChange;

public class HitChange implements GameChange {
    private final int personId;
    private final int damage;

    public HitChange(int personId, int damage) {
        this.personId = personId;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getPersonId() {
        return personId;
    }
}
