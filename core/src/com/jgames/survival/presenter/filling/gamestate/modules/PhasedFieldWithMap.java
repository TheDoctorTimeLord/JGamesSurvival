package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

public class PhasedFieldWithMap<K, D> extends PhasedField<Map<K, D>> {
    private final Function<K, D> dataCreator;

    public PhasedFieldWithMap(Function<K, D> dataCreator, Function<D, D> dataDuplicator) {
        super(HashMap::new, lastMap -> lastMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, e -> dataDuplicator.apply(e.getValue())))
        );
        this.dataCreator = dataCreator;
    }

    @Nullable
    public D getCurrentState(K key) {
        return getCurrentState().get(key);
    }

    @Nullable
    public D getLastState(K key) {
        return getLastState().get(key);
    }

    public D removeFromLastState(K key) {
        return getLastState().remove(key);
    }

    public D getOrCreateLastState(K key) {
        return getOrCreateLastState().computeIfAbsent(key, dataCreator);
    }

    public List<D> getAllCurrentData() {
        return new ArrayList<>(getCurrentState().values());
    }
}
