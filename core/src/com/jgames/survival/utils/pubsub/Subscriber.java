package com.jgames.survival.utils.pubsub;

public interface Subscriber<I> {
    boolean needNotify(I gameChange);
    void subscribe(I information, Publisher<I, ? extends Subscriber<I>> source);
}
