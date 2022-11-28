package com.jgames.survival.model.game.logic.battle.behaviours.commandbased;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.commands.BattleCommandPerformElement;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;
import ru.jengine.battlemodule.core.information.InformationCenter;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.behaviours.commandbased.CommandHandlerManager.MainCommandHandlerBuilder;
import com.jgames.survival.model.game.logic.battle.commands.SelectionFromSetParameters;
import com.jgames.survival.model.game.logic.battle.commands.meleeattack.MeleeAttackCommand;
import com.jgames.survival.model.game.logic.battle.commands.move.MoveCommand;
import com.jgames.survival.model.game.logic.battle.commands.rangedattack.RangedAttack;
import com.jgames.survival.model.game.logic.battle.commands.waiting.WaitingCommand;

@BattleBeanPrototype
public class FighterBehavior extends CommandsBasedBehavior {
    public FighterBehavior() {
        super(MainCommandHandlerBuilder.builder()
                .bindCommand(MeleeAttackCommand.class, (id, command) -> {
                    SelectionFromSetParameters<BattleModel> parametersTemplate = command.createParametersTemplate();
                    BattleModel selected = RandomUtils.chooseInCollection(parametersTemplate.getElements());
                    parametersTemplate.selectModel(selected);

                    return new BattleCommandPerformElement<>(id, command, parametersTemplate);
                })
                .bindCommand(RangedAttack.class, (id, command) -> {
                    SelectionFromSetParameters<BattleModel> parametersTemplate = command.createParametersTemplate();
                    BattleModel selected = RandomUtils.chooseInCollection(parametersTemplate.getElements());
                    parametersTemplate.selectModel(selected);

                    return new BattleCommandPerformElement<>(id, command, parametersTemplate);
                })
                .bindCommand(MoveCommand.class, (id, command) -> {
                    SelectionFromSetParameters<Point> parametersTemplate = command.createParametersTemplate();
                    Point selected = RandomUtils.chooseInCollection(parametersTemplate.getElements());
                    parametersTemplate.selectModel(selected);

                    return new BattleCommandPerformElement<>(id, command, parametersTemplate);
                })
                .bindCommand(WaitingCommand.class, (id, command) ->
                        new BattleCommandPerformElement<>(id, command, NoneParameters.INSTANCE))
                .build((id, commands) -> {
                    List<String> availableCommandNames = commands.stream()
                            .map(command -> command.getClass().getName())
                            .toList();
                    String strCommands = String.join(", ", availableCommandNames);
                    throw new IllegalStateException("Not found any handlers for commands: [%s]".formatted(strCommands));
                })
        );
    }

    @Override
    public Set<Integer> bind(List<BattleModel> dynamicObjects, InformationCenter informationCenter) {
        return dynamicObjects.stream()
                .map(BattleModel::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public void unbind(int unboundedId) {}
}
