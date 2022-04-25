package com.jgames.survival.model.game.logic.battle.vision;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.visible.outside.BaseSectorScanner;
import ru.jengine.battlemodule.standardfilling.visible.outside.TileClassifier;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.fieldofview.shadowcasting.SectorScanner;

import com.jgames.survival.model.game.logic.battle.models.StaticModel;

@Bean
public class SolidSectorScanner implements BaseSectorScanner, TileClassifier {
    private static final String VISIBLE = "visible";
    private static final String SOLID = "solid";

    @Override
    public String classify(List<BattleModel> modelsOnTile) {
        return modelsOnTile.stream().anyMatch(model -> model instanceof StaticModel)
                ? SOLID
                : VISIBLE;
    }

    @Override
    public boolean isBlocking(String tileType) {
        return SOLID.equals(tileType);
    }

    @Nullable
    @Override
    public SectorScanner getScannerForTileType(String tileType) {
        return VISIBLE.equals(tileType)
                ? this
                : null;
    }

    @Override
    public String getVisibleScope() {
        return VisionScopeConstants.VISIBLE;
    }
}
