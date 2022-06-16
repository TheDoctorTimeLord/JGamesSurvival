package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.List;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;

@Bean
public class MapFillingMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = List.of(MapFillingModule.NAME);

    private MapFillingModule mapFillingModule;

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        mapFillingModule = (MapFillingModule)modules[0];
    }

    public void addMapCellItem(Point mapCellItem) {
        mapFillingModule.addMapCellItem(mapCellItem);
        mapFillingModule.markCellAsUpdated(mapCellItem);
    }

    public void markAllMapAsUpdated() {
        for (Point point : mapFillingModule.getBattlefieldCells()) {
            mapFillingModule.markCellAsUpdated(point);
        }
    }
}
