package com.jgames.survival.model.game.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.jengine.battlemodule.core.BattleGenerator;
import ru.jengine.battlemodule.core.IdGenerator;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.utils.RandomUtils;
import ru.test.annotation.battle.model.BattleCharacter;

public class SimpleBattleGenerator implements BattleGenerator {
    private static final int MAP_SIZE = 5;
    private IdGenerator idGenerator;

    @Override
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public BattleState generate() {
        Map<Integer, BattleModel> battleModelById = new HashMap<>();
        Map<Point, List<Integer>> mapPosition = new HashMap<>();
        List<Integer> dynamicModels = new ArrayList<>();
        Set<Point> availablePoints = generateAllPositions();

        for (int i = 0; i < 5; i++) {
            BattleCharacter model = new BattleCharacter(idGenerator.generateId(), 10);
            Point position = RandomUtils.chooseInCollection(availablePoints);
            availablePoints.remove(position);

            battleModelById.put(model.getId(), model);
            mapPosition.computeIfAbsent(position, p -> new ArrayList<>()).add(model.getId());
            model.setPosition(position);
            model.setDirection(RandomUtils.chooseInCollection(Arrays.asList(Direction.values())));
            dynamicModels.add(model.getId());
        }

        return new BattleState(battleModelById, mapPosition, dynamicModels);
    }

    private static Set<Point> generateAllPositions() {
        Set<Point> points = new HashSet<>();
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }
}
