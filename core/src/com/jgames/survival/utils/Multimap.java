package com.jgames.survival.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Multimap<K, V> {
    private final Map<K, Set<V>> map = new HashMap<>();

    public Set<V> get(K key) {
        return map.get(key);
    }

    public void add(K key, V value) {
        map.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }

    public void removeKey(K key) {
        map.remove(key);
    }

    public void removeValues(V value) {
        List<K> keysForRemove = new ArrayList<>();

        for (Entry<K, Set<V>> entry : map.entrySet()) {
            entry.getValue().remove(value);
            if (entry.getValue().isEmpty()) {
                keysForRemove.add(entry.getKey());
            }
        }

        for (K key : keysForRemove) {
            map.remove(key);
        }
    }

    public Map<K, Set<V>> asMap() {
        return map;
    }
}
