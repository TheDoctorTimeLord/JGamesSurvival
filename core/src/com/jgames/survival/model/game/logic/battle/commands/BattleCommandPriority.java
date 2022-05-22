package com.jgames.survival.model.game.logic.battle.commands;

public enum BattleCommandPriority {
    WAIT(0),
    ATTACK(10),
    MOVE(20);

    private final int priority;

    BattleCommandPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
