package com.jgames.survival.model.game.presentation.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.BattlePresenterActionSubscriber;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeStrategy;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeType;
import ru.jengine.beancontainer.annotations.Bean;
import ru.test.annotation.battle.battleactions.initializeBattle.StartPositionAction;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.api.changes.StartPositionData;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.presentation.ToGameChangeMapper;

/**
 * Класс, занимающийся отображением всех действий, произошедших при инициализации боя, в специальный формат для UI.
 */
@Bean
public class InitializationBattleActionMapper implements ToGameChangeMapper, BattlePresenterActionSubscriber {
    private GameChangeSender changeSender;

    @Override
    public void configureMapper(GameChangeSender changeSender, GameBattleHandler battleHandler) {
        this.changeSender = changeSender;
        battleHandler.getBattleMaster()
                .getBattlePresenter()
                .subscribeBattleActions(SubscribeType.INITIALIZATION, SubscribeStrategy.ALL_ACTIONS_SUBSCRIBE_STRATEGY, this);
    }

    @Override
    public void subscribe(SubscribeType subscribeType, Collection<BattleAction> actions) {
        List<StartPositionAction> startPositions = convertToStartPosition(actions);
        if (startPositions != null) {
            changeSender.sendGameChange(new StartPositionData(startPositions));
        }
    }

    private static List<StartPositionAction> convertToStartPosition(Collection<BattleAction> actions) {
        List<StartPositionAction> startPositions = new ArrayList<>();

        for (BattleAction action : actions) {
            if (action instanceof StartPositionAction) {
                startPositions.add((StartPositionAction)action);
            } else {
                Gdx.app.error("ACTION MAPPING", "[" + action + "] was not StartPositionAction");
                return null;
            }
        }

        return startPositions;
    }
}
