package com.jgames.survival.model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.jgames.survival.model.api.GameAction;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.GameChangeListener;
import com.jgames.survival.model.api.GameChangeListenerRegistrar;
import com.jgames.survival.utils.pubsub.Publisher;

public abstract class AbstractGameHandler extends Thread implements GameActionSender, GameChangeListenerRegistrar {
    protected final Queue<GameAction> actionPool = new ConcurrentLinkedQueue<>();
    protected final Publisher<GameChange, GameChangeListener> changesPublisher = new Publisher<>();

    protected volatile boolean isGameRunning = true;

    @Override
    public void sendGameAction(GameAction action) {
        actionPool.add(action);
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void addGameChangesListener(GameChangeListener listener) {
        changesPublisher.addSubscriber(listener);
    }

    public void stopGame() {
        isGameRunning = false;
        synchronized (this) {
            notify();
        }
    }

    public abstract void onStart();

    public abstract void run();
}
