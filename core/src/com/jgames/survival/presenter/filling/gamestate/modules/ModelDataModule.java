package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;
import com.jgames.survival.presenter.filling.gamestate.presenters.ModelDataPresenter;

public class ModelDataModule implements PresentingStateModule<ModelDataPresenter>, ModelDataPresenter {
    public static final String NAME = "modelData";

    private final Deque<Map<Integer, ModelData>> modelsDataStates = new ArrayDeque<>();

    public synchronized void setPositionData(int modelId, Point startedPosition, Direction direction) {
        getOrCreateLastModelState(modelId)
                .setPosition(startedPosition)
                .setDirection(direction);
    }

    public synchronized ModelData getLastModelState(int modelId) {
        return modelsDataStates.getLast().get(modelId);
    }

    public synchronized void addState() {
        Map<Integer, ModelData> newState = modelsDataStates.getLast()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, e -> new ModelData(e.getValue())));
        modelsDataStates.addLast(newState);
    }

    @Override
    public synchronized List<ModelData> getDataForAllModels() {
        return new ArrayList<>(modelsDataStates.getFirst().values());
    }

    @Override
    public synchronized ModelData getCurrentModelState(int modelId) {
        return modelsDataStates.getFirst().get(modelId);
    }

    @Override
    public synchronized void updateToNextPhase() {
        if (modelsDataStates.size() > 1) {
            modelsDataStates.poll();
        }
    }

    @Override
    public synchronized boolean isLastPhase() {
        return modelsDataStates.size() <= 1;
    }

    public synchronized ModelData getOrCreateLastModelState(int modelId) {
        if (modelsDataStates.isEmpty()) {
            modelsDataStates.addLast(new HashMap<>());
        }

        return modelsDataStates.getLast().computeIfAbsent(modelId, ModelData::new);
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public ModelDataPresenter getPresenter() {
        return this;
    }
}
