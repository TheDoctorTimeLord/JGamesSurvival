package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.test.annotation.battle.battleactions.MoveBattleAction;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.gamechangeshangling.PresentingGameState;
import com.jgames.survival.control.uiscripts.EmptyScriptState;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;

public class ApplyPhaseChanges implements UIScriptElement<EmptyScriptState> {
    private final PresentingGameState gameState;
    private final GlobalMapWrapper<MapCell> globalMap;
    private final TextureRegion[] directedPersonsTextures;

    public ApplyPhaseChanges(PresentingGameState gameState, GlobalMapWrapper<MapCell> globalMap,
            TextureRegion[] directedPersonsTextures) {
        this.gameState = gameState;
        this.globalMap = globalMap;
        this.directedPersonsTextures = directedPersonsTextures;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return null;
    }

    @Override
    public boolean isRunnableElement() {
        return true;
    }

    @Override
    public boolean isValid(UIAction action) {
        return true;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        List<BattleAction> actions = gameState.getPhase().getChanges();

        //Ставим на новые места персонажей
        Set<Point> occupiedPlaces = new HashSet<>();
        for (BattleAction action : actions) {
            if (action instanceof MoveBattleAction) {
                MoveBattleAction moveAction = (MoveBattleAction)action;
                Point oldPosition = moveAction.getOldPosition();
                Point newPosition = moveAction.getNewPosition();
                Direction direction = Direction.getByOffset(newPosition.sub(oldPosition));

                globalMap.getTableCell(newPosition.getY(), newPosition.getX()).setTexture(directedPersonsTextures[direction.ordinal()]);
                occupiedPlaces.add(moveAction.getNewPosition());
            }
        }

        //Освобождаем пустые позиции
        for (BattleAction action : actions) {
            if (action instanceof  MoveBattleAction) {
                MoveBattleAction moveAction = (MoveBattleAction)action;
                Point oldPosition = moveAction.getOldPosition();

                if (!occupiedPlaces.contains(oldPosition)) {
                    globalMap.getTableCell(oldPosition.getY(), oldPosition.getX()).resetTexture();
                }
            }
        }
    }

    @Override
    public boolean rollback(UIAction action, EmptyScriptState state) {
        return true;
    }
}
