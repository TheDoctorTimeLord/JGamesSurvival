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
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;
import com.jgames.survival.model.game.logic.battle.commands.meleeattack.MeleeAttackCommand;
import com.jgames.survival.model.game.logic.battle.commands.move.MoveCommand;
import com.jgames.survival.model.game.logic.battle.commands.rangedattack.RangedAttack;
import com.jgames.survival.model.game.logic.battle.commands.waiting.WaitingCommand;

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
        WaitingCommand waitingCommand = null;
        MoveCommand moveCommand = null;
        RangedAttack rangedAttack = null;
        MeleeAttackCommand meleeAttackCommand = null;

        for (BattleCommand<?> availableCommand : availableCommands) {
            if (availableCommand instanceof MeleeAttackCommand meleeAttack) {
                meleeAttackCommand = meleeAttack;
            }
            if (availableCommand instanceof RangedAttack ranged) {
                rangedAttack = ranged;
            }
            if (availableCommand instanceof MoveCommand move) {
                moveCommand = move;
            }
            if (availableCommand instanceof WaitingCommand wait) {
                waitingCommand = wait;
            }
        }

        if (meleeAttackCommand != null) {
            SelectionFromSetParameters<BattleModel> parametersTemplate = meleeAttackCommand.createParametersTemplate();
            BattleModel selected = RandomUtils.chooseInCollection(parametersTemplate.getElements());
            parametersTemplate.selectModel(selected);

            return new BattleCommandPerformElement<>(characterId, meleeAttackCommand, parametersTemplate);
        }

        if (rangedAttack != null) {
            SelectionFromSetParameters<BattleModel> parametersTemplate = rangedAttack.createParametersTemplate();
            BattleModel selected = RandomUtils.chooseInCollection(parametersTemplate.getElements());
            parametersTemplate.selectModel(selected);

            return new BattleCommandPerformElement<>(characterId, rangedAttack, parametersTemplate);
        }

        if (moveCommand != null) {
            SelectionFromSetParameters<Point> parametersTemplate = moveCommand.createParametersTemplate();
            Point selected = RandomUtils.chooseInCollection(parametersTemplate.getElements());
            parametersTemplate.selectModel(selected);

            return new BattleCommandPerformElement<>(characterId, moveCommand, parametersTemplate);
        }

        return new BattleCommandPerformElement<>(characterId, waitingCommand, NoneParameters.INSTANCE);
    }

    @Override
    public BattleCommandPerformElement<?> handleAdditionalCommand(int characterId, AdditionalBattleCommand<?> command) {
        return null;
    }
}
