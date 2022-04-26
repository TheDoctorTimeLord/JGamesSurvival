package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;
import java.util.Optional;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.ui.constants.HardcodeObjectNames;
import com.jgames.survival.ui.constants.SpecialObjectsNames;

public class TintResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<ModelData> cellModelData) {
        Optional<ModelData> modelData = cellModelData.stream()
                .filter(model -> HardcodeObjectNames.PERSON.equals(model.getTypeName()))
                .findFirst();

        if (modelData.isPresent()) {
            ModelData modelDataInt = modelData.get();
            if (modelDataInt.isKilled()) {
                return List.of(new ResolvingContext(
                        SpecialObjectsNames.TINT,
                        new DrawingContext().setModelData(modelData.get())
                ));
            }
        }

        return List.of();
    }
}
