package com.jgames.survival.model.game.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import ru.jengine.battlemodule.core.BattleGenerator;
import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.core.state.BattleStateBuilder;
import ru.jengine.battlemodule.core.state.BattlefieldLimiter;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.attributes.AttributeGenerator;
import com.jgames.survival.model.game.logic.battle.limirers.RectangleBattleFieldLimiter;
import com.jgames.survival.model.game.logic.battle.models.Entity;
import com.jgames.survival.model.game.logic.battle.models.EntityType;
import com.jgames.survival.model.game.logic.battle.models.StaticModel;
import com.jgames.survival.model.game.logic.battle.models.StaticModelType;

/**
 * Базовая генерация состояния боя.
 */
public class InitialBattleGenerator extends BattleGenerator {
    private static final int MAP_WIDTH = 6;
    private static final int START_HEIGHT = 3;
    private static final int BETWEEN_HEIGHT = 3;
    public static final String TEAM_ATTRIBUTE = "team";

    /**
     * Генерирует начальное состояние боя с динамическими и статическими объектами.
     * @return BattleState - объект класса, который хранит всю информацию о текущем состоянии боя.
     */
    @Override
    public BattleState generate() {
        List<BattleModel> dynamicModels = new ArrayList<>();
        List<BattleModel> staticModels = new ArrayList<>();

        BattlefieldLimiter teamAIArea =
                new RectangleBattleFieldLimiter(PointPool.obtain(0, 0), MAP_WIDTH, START_HEIGHT);
        BattlefieldLimiter betweenArea =
                new RectangleBattleFieldLimiter(PointPool.obtain(0, START_HEIGHT), MAP_WIDTH, BETWEEN_HEIGHT);
        BattlefieldLimiter teamStandardArea =
                new RectangleBattleFieldLimiter(PointPool.obtain(0, START_HEIGHT + BETWEEN_HEIGHT), MAP_WIDTH, BETWEEN_HEIGHT);

        EntityType fighterType = new EntityType("fighter", AttributeGenerator.getFighterAttributesKit());
        EntityType archerType = new EntityType("archer", AttributeGenerator.getArcherAttributesKit());
        StaticModelType wallType = new StaticModelType("wall", new AttributesContainer());

        Set<Point> availableAiArea = teamAIArea.getPointsInBound();
        Set<Point> availableStandardArea = teamStandardArea.getPointsInBound();

        staticModels.addAll(generateStaticModelsInArea(wallType, betweenArea.getPointsInBound(), 7));
        staticModels.addAll(generateStaticModelsInArea(wallType, availableAiArea, 3));
        staticModels.addAll(generateStaticModelsInArea(wallType, availableStandardArea, 3));

        makePathFromAiToStandard(staticModels);

        dynamicModels.addAll(moveToArea(generateTeam(fighterType, archerType, "ai"), availableAiArea));
        dynamicModels.addAll(moveToArea(generateTeam(fighterType, archerType, "standard"), availableStandardArea));

        return BattleStateBuilder
                .createByModels(staticModels, dynamicModels)
                .battlefieldLimiters(teamAIArea, betweenArea, teamStandardArea)
                .build();
    }

    private void makePathFromAiToStandard(List<BattleModel> staticModels) {

    }

    private List<Entity> moveToArea(List<Entity> entities, Set<Point> availablePositions)
    {
        for (Entity entity : entities) {
            Point point = RandomUtils.chooseInCollection(availablePositions);
            entity.setPosition(point);
            entity.setDirection(RandomUtils.chooseInCollection(Arrays.asList(Direction.values())));
            availablePositions.remove(point);
        }
        return entities;
    }

    private List<Entity> generateTeam(EntityType fighterType, EntityType archerType, String teamName)
    {
        List<Entity> team = new ArrayList<>();

        team.addAll(generateEntities(fighterType, teamName, 3));
        team.addAll(generateEntities(archerType, teamName, 2));

        return team;
    }

    private List<Entity> generateEntities(EntityType entityType, String teamName, int count)
    {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    Entity entity = entityType.createBattleModelByType(idGenerator.generateId());
                    entity.getAttributes().add(new StringAttribute(TEAM_ATTRIBUTE, teamName));
                    return entity;
                })
                .toList();
    }

    private List<StaticModel> generateStaticModelsInArea(StaticModelType staticModelType, Set<Point> area, int count)
    {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    StaticModel staticModel = staticModelType.createBattleModelByType(idGenerator.generateId());

                    Point point = RandomUtils.chooseInCollection(area);
                    area.remove(point);
                    staticModel.setPosition(point);

                    return staticModel;
                })
                .toList();
    }
}
