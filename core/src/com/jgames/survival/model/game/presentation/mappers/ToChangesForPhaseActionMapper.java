package com.jgames.survival.model.game.presentation.mappers;

import java.util.Collection;
import java.util.Objects;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.BattlePresenterActionSubscriber;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeStrategy;
import ru.jengine.battlemodule.core.battlepresenter.SubscribeType;
import ru.jengine.beancontainer.annotations.Bean;
import ru.test.annotation.battle.battleactions.HitBattleAction;
import ru.test.annotation.battle.battleactions.MoveBattleAction;
import ru.test.annotation.battle.battleactions.RotateAction;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.NewPhase;
import com.jgames.survival.model.api.changes.HitChange;
import com.jgames.survival.model.api.changes.MoveChange;
import com.jgames.survival.model.api.changes.RotateChange;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.presentation.ToGameChangeMapper;

/**
 * Класс, занимающийся отображением всех действий, произошедших за одну фазу боя, в специальный формат для UI.
 */
@Bean
public class ToChangesForPhaseActionMapper implements ToGameChangeMapper, BattlePresenterActionSubscriber {
    private GameChangeSender changeSender;

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
                .map(ToChangesForPhaseActionMapper::convertActionToChange)
                .filter(Objects::nonNull)
                .forEach(changeSender::sendGameChange);
    }

    private static GameChange convertActionToChange(BattleAction battleAction) {
        if (battleAction instanceof MoveBattleAction) {
            MoveBattleAction action = (MoveBattleAction)battleAction;
            return new MoveChange(action.getCharacterId(), action.getOldPosition(), action.getNewPosition());
        }
        if (battleAction instanceof RotateAction) {
            RotateAction action = (RotateAction)battleAction;
            return new RotateChange(action.getCharacterId(), action.getRotateTo());
        }
        if (battleAction instanceof HitBattleAction) {
            HitBattleAction action = (HitBattleAction)battleAction;
            return new HitChange(action.getTarget(), action.getDamagePoints());
        }

        Gdx.app.error("ACTION MAPPING", "[" + battleAction + "] was not handled");
        return null;
    }
}
