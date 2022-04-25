package com.jgames.survival.model.game.logic.battle.behaviours;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.behaviors.Behavior;
import ru.jengine.battlemodule.core.commands.AdditionalBattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommandPerformElement;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;
import ru.jengine.battlemodule.core.information.InformationCenter;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.commands.MeleeAttackCommand;
import com.jgames.survival.model.game.logic.battle.commands.MeleeAttackParameters;
import com.jgames.survival.model.game.logic.battle.commands.RangedAttack;
import com.jgames.survival.model.game.logic.battle.commands.RangedAttackParameters;
import com.jgames.survival.model.game.logic.battle.commands.WaitCommand;

@BattleBeanPrototype
public class FighterBehavior implements Behavior {
    @Override
    public Set<Integer> bind(List<BattleModel> dynamicObjects, InformationCenter informationCenter) {
        return dynamicObjects.stream()
                .map(BattleModel::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public void unbind(int unboundedId) {

    }

    @Override
    public BattleCommandPerformElement<?> sendAction(int characterId, List<BattleCommand<?>> availableCommands) {
        WaitCommand waitCommand = null;
        RangedAttack rangedAttack = null;
        MeleeAttackCommand meleeAttackCommand = null;

        for (BattleCommand<?> availableCommand : availableCommands) {
            if (availableCommand instanceof MeleeAttackCommand meleeAttack) {
                meleeAttackCommand = meleeAttack;
            }
            if (availableCommand instanceof RangedAttack ranged) {
                rangedAttack = ranged;
            }
            if (availableCommand instanceof WaitCommand wait) {
                waitCommand = wait;
            }
        }

        if (meleeAttackCommand != null) {
            MeleeAttackParameters parametersTemplate = meleeAttackCommand.createParametersTemplate();
            BattleModel selected = RandomUtils.chooseInCollection(parametersTemplate.getEnemies());
            parametersTemplate.selectEnemy(selected);

            return new BattleCommandPerformElement<>(characterId, meleeAttackCommand, parametersTemplate);
        }

        if (rangedAttack != null) {
            RangedAttackParameters parametersTemplate = rangedAttack.createParametersTemplate();
            BattleModel selected = RandomUtils.chooseInCollection(parametersTemplate.getVisibleModels());
            parametersTemplate.selectEnemy(selected);

            return new BattleCommandPerformElement<>(characterId, rangedAttack, parametersTemplate);
        }

        return new BattleCommandPerformElement<>(characterId, waitCommand, NoneParameters.INSTANCE);
    }

    @Override
    public BattleCommandPerformElement<?> handleAdditionalCommand(int characterId, AdditionalBattleCommand<?> command) {
        return null;
    }
}
