package com.jgames.survival.model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ru.jengine.utils.Logger;

import com.jgames.survival.model.api.interaction.GameAction;
import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.GameChangeListener;
import com.jgames.survival.model.api.interaction.GameChangeListenerRegistrar;
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

    @Override
    public void removeGameChangeListener(GameChangeListener listener) {
        changesPublisher.removeSubscriber(listener);
    }

    public void stopGame() {
        isGameRunning = false;
        synchronized (this) {
            notify();
        }
    }

    public abstract void onStart();

    public abstract void run();

    public abstract Logger getLogger();
}
