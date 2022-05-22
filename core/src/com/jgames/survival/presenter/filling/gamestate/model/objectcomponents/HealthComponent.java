package com.jgames.survival.presenter.filling.gamestate.model.objectcomponents;

import com.jgames.survival.presenter.filling.gamestate.model.GameObjectComponent;

public class HealthComponent extends GameObjectComponent {
    private final int maxHp;
    private volatile int hp;

    public HealthComponent(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public synchronized int getHp() {
        return hp;
    }

    public synchronized void damage(int damage) {
        if (hp > 0) {
            hp -= damage;
            hp = Math.max(hp, 0);
        }
    }

    public synchronized void kill() {
        hp = 0;
    }

    public synchronized boolean isKilled() {
        return hp == 0;
    }
}
