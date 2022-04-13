package com.jgames.survival.model.game.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.jengine.battlemodule.core.BattleGenerator;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.utils.ObjectPlacementUtils;

/**
 * Базовая генерация состояния боя.
 */
public class InitialBattleGenerator extends BattleGenerator {
    private static final int MAP_SIZE = 5;

    /**
     * Генерирует начальное состояние боя с динамическими и статическими объектами.
     * @return BattleState - объект класса, который хранит всю информацию о текущем состоянии боя.
     */
    @Override
    public BattleState generate() {
        Map<Point, List<Integer>> mapPosition = new HashMap<>();
        List<BattleModel> dynamicModels = new ArrayList<>();
        List<BattleModel> staticModels = new ArrayList<>();
        BattlefieldLimiter battleFieldLimiter = new SquareBattleFieldLimiter(PointPool.obtain(0, 0), MAP_SIZE);

        for (int i = 0; i < 5; i++) {
            DynamicModel model = new DynamicModel(idGenerator.generateId());
            Point position = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);

            mapPosition.computeIfAbsent(position, p -> new ArrayList<>()).add(model.getId());
            model.setPosition(position);
            model.setDirection(RandomUtils.chooseInCollection(Arrays.asList(Direction.values())));
            dynamicModels.add(model);
        }

        for (int i = 0; i < 5; i++) {
            StaticModel model = new StaticModel(idGenerator.generateId());
            Point position = ObjectPlacementUtils.getFreeCell(mapPosition, battleFieldLimiter);

            mapPosition.computeIfAbsent(position, p -> new ArrayList<>()).add(model.getId());
            model.setPosition(position);
            staticModels.add(model);
        }

        return new BattleState(staticModels, dynamicModels, battleFieldLimiter);
    }
}
