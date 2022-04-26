package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;
import java.util.Optional;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.ui.constants.HardcodeObjectNames;

public class WallResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<ModelData> cellModelData) {
        Optional<ModelData> modelData = cellModelData.stream()
                .filter(model -> HardcodeObjectNames.WALL.equals(model.getTypeName()))
                .findFirst();

        if (modelData.isEmpty()) {
            return List.of();
        }

        return List.of(
                new ResolvingContext(HardcodeObjectNames.WALL, new DrawingContext().setModelData(modelData.get()))
        );
    }
}
