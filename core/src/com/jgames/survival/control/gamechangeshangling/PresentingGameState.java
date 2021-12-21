package com.jgames.survival.control.gamechangeshangling;

import java.util.ArrayDeque;
import java.util.Deque;

import com.jgames.survival.model.api.changes.ChangesForPhase;
import com.jgames.survival.model.api.changes.StartPositionData;

public class PresentingGameState { //TODO сейчас инкапсуляция работает на добром слове. Нужно кардинально поменять всё
    private StartPositionData startPositions;
    private final Deque<ChangesForPhase> changesOnPhase = new ArrayDeque<>();

    public synchronized void addChangesToCurrentPhase(ChangesForPhase gameChange) {
        changesOnPhase.addLast(gameChange);
    }

    public synchronized ChangesForPhase getPhase() {
        return changesOnPhase.pollFirst();
    }

    public StartPositionData getStartPositions() {
        return startPositions;
    }

    public synchronized boolean isPhasesEmpty() {
        return changesOnPhase.isEmpty();
    }

    public void setStartPositions(StartPositionData startPositions) {
        this.startPositions = startPositions;
    }
}
