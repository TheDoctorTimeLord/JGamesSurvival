package com.jgames.survival.utils;

import java.util.LinkedList;

public class PoolableLinkedDeque<T> extends LinkedList<T> implements PoolableDeque<T> {
    @Override
    public void reset() {
        clear();
    }

    public PoolableLinkedDeque<T> addWithChain(T element) {
        add(element);
        return this;
    }
}
