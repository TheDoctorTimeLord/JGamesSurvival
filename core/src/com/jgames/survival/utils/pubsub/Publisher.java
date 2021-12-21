package com.jgames.survival.utils.pubsub;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Publisher<I, S extends Subscriber<I>> {
    private final List<Subscriber<I>> subscribers = new CopyOnWriteArrayList<>();

    public synchronized void addSubscriber(S subscriber) {
        subscribers.add(subscriber);
    }

    public synchronized void removeSubscriber(S subscriber) {
        subscribers.remove(subscriber);
    }

    public void notify(I information) {
        subscribers.forEach(subscriber -> {
            if (subscriber.needNotify(information)) {
                subscriber.notify(information, this);
            }
        });
    }
}
