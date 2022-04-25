package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelData;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelDataModule;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.utils.MoveUtils;

public class ModelDataMutator implements PresentingStateModuleMutator { //TODO перенести синхронизацию сюда из модулей
    private static final List<String> USED_MODULE_NAMES = Arrays.asList(ModelDataModule.NAME, MapFillingModule.NAME);

    private ModelDataModule modelData;
    private MapFillingModule mapFilling;

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        modelData = (ModelDataModule) modules[0];
        mapFilling = (MapFillingModule) modules[1];
    }

    public void setHp(int modelId, int hp) {
        modelData.getOrCreateLastModelState(modelId)
                .setMaxHp(hp);
    }

    public void setPositionData(int modelId, Point startPosition, @Nullable Direction direction) {
        modelData.getOrCreateLastModelState(modelId)
                .setPosition(startPosition)
                .setDirection(direction);

        mapFilling.updateObjectsOnCell(startPosition, getObjectIds(modelId));
        mapFilling.markCellAsUpdated(startPosition);
    }

    public void setObjectType(int modelId, String objectTypeName) {
        modelData.getOrCreateLastModelState(modelId).setTypeName(objectTypeName);
    }

    public void moveModel(int modelId, Point newPosition) {
        Point lastPosition = modelData.getLastModelState(modelId).getPosition();
        ModelData data = modelData.getLastModelState(modelId);
        data.setDirection(MoveUtils.getRotate(lastPosition, newPosition));
        data.setPosition(newPosition);

        mapFilling.updateObjectsOnCell(lastPosition, Collections.emptyList());
        mapFilling.updateObjectsOnCell(newPosition, getObjectIds(data.getId()));

        mapFilling.markCellAsUpdated(lastPosition);
        mapFilling.markCellAsUpdated(newPosition);
    }

    public void rotateModel(int modelId, Direction newDirection) {
        ModelData modelState = modelData.getLastModelState(modelId);
        modelState.setDirection(newDirection);

        mapFilling.markCellAsUpdated(modelState.getPosition());
    }

    public void damageModel(int modelId, int damagePoints) {
        ModelData modelState = modelData.getLastModelState(modelId);
        modelState.damage(damagePoints);

        mapFilling.markCellAsUpdated(modelState.getPosition());
    }

    public void startNewPhase() {
        modelData.addState();
        mapFilling.addState();
    }

    private Collection<Integer> getObjectIds(@Nullable Integer objectId) {
        return objectId != null && objectId != Integer.MIN_VALUE ? List.of(objectId) : Collections.emptyList();
    }
}
