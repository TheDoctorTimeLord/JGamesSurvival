package com.jgames.survival.utils.pubsub;

public interface Subscriber<I> {
    boolean needNotify(I information);
    void notify(I information, Publisher<I, ? extends Subscriber<I>> source);
}
