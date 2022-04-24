package com.jgames.survival.model.game.presentation.mappers;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.BattlePresenterActionSubscriber;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeStrategy;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeType;
import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.phase.NewPhase;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.presentation.ToGameChangeMapper;

/**
 * Класс, занимающийся отображением всех действий, произошедших за одну фазу боя, в специальный формат для UI.
 */
@Bean
public class ToChangesForPhaseActionMapper implements ToGameChangeMapper, BattlePresenterActionSubscriber {
    private final List<PhaseActionMapper> actionMappers;
    private GameChangeSender changeSender;

    public ToChangesForPhaseActionMapper(List<PhaseActionMapper> actionMappers) {
        this.actionMappers = actionMappers.stream()
                .sorted(Comparator.comparingInt(ActionMapper::getMapperPriority))
                .collect(Collectors.toList());
    }

    @Override
    public void configureMapper(GameChangeSender changeSender, GameBattleHandler battleHandler) {
        this.changeSender = changeSender;
        battleHandler.getBattleMaster().getBattlePresenter()
                .subscribeBattleActions(SubscribeType.PHASE, SubscribeStrategy.ALL_ACTIONS_SUBSCRIBE_STRATEGY, this);
    }

    @Override
    public void subscribe(SubscribeType subscribeType, Collection<BattleAction> actions) {
        if (actions.isEmpty()) {
            return;
        }

        changeSender.sendGameChange(new NewPhase());
        actions.stream()
                .flatMap(this::convertActionToChange)
                .forEach(changeSender::sendGameChange);
    }

    private Stream<GameChange> convertActionToChange(BattleAction battleAction) {
        for (ActionMapper actionMapper : actionMappers) {
            if (actionMapper.isCanMap(battleAction)) {
                return actionMapper.map(battleAction);
            }
        }

        Gdx.app.error("ACTION MAPPING", "[" + battleAction + "] was not handled");
        return Stream.empty();
    }
}
