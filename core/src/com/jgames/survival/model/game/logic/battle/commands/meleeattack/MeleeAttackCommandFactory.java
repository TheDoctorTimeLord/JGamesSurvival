package com.jgames.survival.model.game.logic.battle.commands.meleeattack;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;
import com.jgames.survival.model.game.logic.battle.commands.meleeattack.meleeattackstrategies.ChooseDamagedBodyPartStrategy;
import com.jgames.survival.model.game.logic.battle.models.CanHit;
import com.jgames.survival.model.game.logic.battle.utils.LocationUtils;

/**
 * Описывает фабрику ближнего боя, создающую объект команды.
 * В обязанности фабрики также входит определить может ли конкретный динамический объект
 * выполнять данную команду в бою и доступна ли эта команда объекту в текущем ходу.
 */
@Bean
public class MeleeAttackCommandFactory implements BattleCommandFactory<SelectionFromSetParameters<BattleModel>, MeleeAttackCommand> {
    private final ChooseDamagedBodyPartStrategy chooseDamagedBodyPartStrategy;

    public MeleeAttackCommandFactory(ChooseDamagedBodyPartStrategy strategy) {
        this.chooseDamagedBodyPartStrategy = strategy;
    }

    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return model instanceof CanHit canHit && canHit.canHit();
    }

    @Override
    public boolean isAvailableCommand(BattleModel model, BattleContext battleContext) {
        return model instanceof CanHit canHit && hasOpponentsNearby(canHit, battleContext.getBattleState());
    }

    @Override
    public MeleeAttackCommand createBattleCommand(BattleModel model, BattleContext battleContext) {
        return new MeleeAttackCommand(getNearestBattleModels((CanHit)model, battleContext.getBattleState()),
                chooseDamagedBodyPartStrategy);
    }

    /**
     * Проверяет наличие противников рядом с бойцом
     * @param battleState контекст битвы
     * @return true, если рядом есть противники, иначе false
     */
    private static boolean hasOpponentsNearby(CanHit model, BattleState battleState) {
        Set<BattleModel> enemies = getNearestBattleModels(model, battleState);
        return !enemies.isEmpty();
    }

    public static Set<BattleModel> getNearestBattleModels(CanHit model, BattleState battleState) {
        Point modelPosition = model.getPosition();
        Direction modelDirection = model.getDirection();
        List<Point> pointNeighbour = LocationUtils.getNeighbours(modelPosition, battleState,
                LocationUtils.getThreeFrontOffsets(modelDirection));

        return pointNeighbour.stream()
                .map(battleState::getModelsOnPosition)
                .flatMap(Collection::stream)
                .filter(battleModel -> battleModel instanceof DynamicModel)
                .collect(Collectors.toSet());
    }
}
