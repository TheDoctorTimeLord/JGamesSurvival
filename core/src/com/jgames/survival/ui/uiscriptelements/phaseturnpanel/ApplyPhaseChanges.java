package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonData;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonDataModule;
import com.jgames.survival.presenter.filling.gamestate.modules.UpdatedCellsModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.PersonDataPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.UpdatedCellsPresenter;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapHelper;

/**
 * Исполняемый шаг скрипта, применяющий все изменения в игре, которые были произведены в рамках одной фазы
 */
public class ApplyPhaseChanges implements UIRunnableScript<EmptyScriptState> {
    private final PersonDataPresenter personDataPresenter;
    private final UpdatedCellsPresenter updatedCellsPresenter;
    private final GlobalMapWrapper<MapCell> globalMap;
    private final TextureRegion[] directedPersonsTextures;

    public ApplyPhaseChanges(PresentingGameState gameState, GlobalMapWrapper<MapCell> globalMap,
            TextureRegion[] directedPersonsTextures) {
        this.personDataPresenter = gameState.getModulePresenter(PersonDataModule.NAME);
        this.updatedCellsPresenter = gameState.getModulePresenter(UpdatedCellsModule.NAME);
        this.globalMap = globalMap;
        this.directedPersonsTextures = directedPersonsTextures;
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        personDataPresenter.updateToNextPhase();
        updatedCellsPresenter.updateToNextPhase();

        for (Point updated : updatedCellsPresenter.getUpdatedCells()) {
            Integer id = personDataPresenter.getPersonOnPosition(updated);
            if (id == null) {
                resetMapCell(updated);
                continue;
            }

            PersonData personState = personDataPresenter.getCurrentPersonState(id);
            if (personState.isKilled()) {
                fillMapCellWithCorpse(personState);
                continue;
            }

            fillMapCell(personState);
        }
    }

    private void resetMapCell(Point coordinate) {
        MapHelper.makeCellEmpty(globalMap.getTableCell(coordinate.getX(), coordinate.getY()));
    }

    private void fillMapCell(PersonData personState) {
        Point position = personState.getPosition();

        MapHelper.createCellFilling(globalMap.getTableCell(position.getX(), position.getY()), true)
                .setMainTexture(directedPersonsTextures[personState.getDirection().ordinal()])
                .setHpLabel(personState.getHp())
                .build();
    }

    private void fillMapCellWithCorpse(PersonData personState) {
        Point position = personState.getPosition();
        MapCell cell = globalMap.getTableCell(position.getX(), position.getY());

        MapHelper.createCellFilling(cell, true)
                .setMainTexture(directedPersonsTextures[personState.getDirection().ordinal()])
                .setHpLabel(personState.getHp())
                .setTint(cell.getDefaultTexture())
                .build();
    }
}
