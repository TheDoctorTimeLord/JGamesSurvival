package com.jgames.survival.model.game.presentation.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.BattleActionPresenter;
import ru.jengine.battlemodule.core.battlepresenter.BattlePresenterActionSubscriber;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeStrategy;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeType;
import ru.jengine.beancontainer.annotations.Bean;
import ru.test.annotation.battle.battleactions.initializeBattle.StartPositionAction;

import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.api.changes.ChangesForPhase;
import com.jgames.survival.model.api.changes.StartPositionData;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.presentation.ToGameChangeMapper;

//TODO сейчас нет корректной работы с боем, нужно лучше связать подписку на действия ~~~
@Bean
public class CommonBattleActionMapper implements ToGameChangeMapper, BattlePresenterActionSubscriber {
    private GameChangeSender changeSender;

    @Override
    public void configureMapper(GameChangeSender changeSender, GameBattleHandler battleHandler) {
        this.changeSender = changeSender;
        BattleActionPresenter battlePresenter = battleHandler.getBattleMaster().getBattlePresenter();
        battlePresenter.subscribeBattleActions(SubscribeType.INITIALIZATION, SubscribeStrategy.ALL_ACTIONS_SUBSCRIBE_STRATEGY, this);
        battlePresenter.subscribeBattleActions(SubscribeType.PHASE, SubscribeStrategy.ALL_ACTIONS_SUBSCRIBE_STRATEGY, this);
    }

    @Override
    public void subscribe(SubscribeType subscribeType, Collection<BattleAction> actions) {
        if (SubscribeType.INITIALIZATION.equals(subscribeType)) {
            List<StartPositionAction> startPositions = convertToStartPosition(actions);
            if (startPositions != null) {
                changeSender.sendGameChange(new StartPositionData(startPositions));
            }
        } else if (SubscribeType.PHASE.equals(subscribeType)) {
            changeSender.sendGameChange(new ChangesForPhase(convertToList(actions)));
        }
    }

    private static List<StartPositionAction> convertToStartPosition(Collection<BattleAction> actions) {
        List<StartPositionAction> startPositions = new ArrayList<>();

        for (BattleAction action : actions) {
            if (action instanceof StartPositionAction) {
                startPositions.add((StartPositionAction)action);
            } else {
                return null;
            }
        }

        return startPositions;
    }

    private static List<BattleAction> convertToList(Collection<BattleAction> actions) {
        return actions instanceof List ? (List<BattleAction>)actions : new ArrayList<>(actions);
    }
}
