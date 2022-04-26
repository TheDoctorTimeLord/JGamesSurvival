package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;
import java.util.Optional;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.ui.constants.HardcodeObjectNames;

public class PersonResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<ModelData> cellModelData) {
        Optional<ModelData> modelData = cellModelData.stream()
                .filter(model -> HardcodeObjectNames.PERSON.equals(model.getTypeName()))
                .findFirst();

        if (modelData.isEmpty()) {
            return List.of();
        }

        DrawingContext context = new DrawingContext().setModelData(modelData.get());
        return List.of(
                new ResolvingContext(HardcodeObjectNames.PERSON, context),
                new ResolvingContext(HardcodeObjectNames.PERSON_HP, context)
        );
    }
}
