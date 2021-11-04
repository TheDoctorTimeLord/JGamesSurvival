package com.jgames.survival.utils;

import java.util.Deque;

import com.badlogic.gdx.utils.Pool.Poolable;

public interface PoolableDeque<T> extends Deque<T>, Poolable {
}
