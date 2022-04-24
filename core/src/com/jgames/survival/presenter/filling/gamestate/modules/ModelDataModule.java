package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.ModelDataPresenter;

public class ModelDataModule implements PresentingStateModule<ModelDataPresenter>, ModelDataPresenter {
    public static final String NAME = "modelData";

    private final Map<Integer, Deque<ModelData>> modelsDataStates = new HashMap<>();
    private final Map<Point, Integer> currentModelsPosition = new HashMap<>();

    public synchronized void setPositionData(int modelId, Point startedPosition, Direction direction) {
        getOrCreateLastModelState(modelId)
                .setPosition(startedPosition)
                .setDirection(direction);
        currentModelsPosition.put(startedPosition, modelId);
    }

    public synchronized ModelData getLastModelState(int modelId) {
        return modelsDataStates.get(modelId).getLast();
    }

    public synchronized void addState() {
        modelsDataStates.forEach((id, states) -> states.addLast(new ModelData(states.getLast())));
    }

    @Override
    public synchronized List<ModelData> getDataForAllModels() {
        return modelsDataStates.values().stream()
                .map(Deque::getFirst)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized ModelData getCurrentModelState(int modelId) {
        return modelsDataStates.get(modelId).getFirst();
    }

    @Override
    public synchronized void updateToNextPhase() {
        modelsDataStates.values().stream()
                .map(Deque::getFirst)
                .filter(modelData -> !modelData.isKilled())
                .forEach(modelData -> currentModelsPosition.remove(modelData.getPosition()));
        modelsDataStates.forEach((id, states) -> {
            if (states.size() > 1) {
                states.removeFirst();
            }
        });
        modelsDataStates.values().stream()
                .map(Deque::getFirst)
                .filter(ModelData::isKilled)
                .filter(modelData -> currentModelsPosition.containsKey(modelData.getPosition()))
                .forEach(modelData -> currentModelsPosition.put(modelData.getPosition(), modelData.getId()));
    }

    @Override
    public synchronized boolean isLastPhase() {
        return modelsDataStates.values().stream()
                .mapToInt(Deque::size)
                .max()
                .orElse(0) <= 1;
    }

    public synchronized ModelData getOrCreateLastModelState(int modelId) {
        return modelsDataStates.computeIfAbsent(modelId, id -> {
            Deque<ModelData> deque = new ArrayDeque<>();
            deque.add(new ModelData(id));
            return deque;
        }).getLast();
    }

    @Override
    @Nullable
    public synchronized Integer getModelOnPosition(Point position) {
        return currentModelsPosition.get(position);
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
