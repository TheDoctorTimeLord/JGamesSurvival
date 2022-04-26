package com.jgames.survival.ui.uiscriptelements.phaseturnpanel;

import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelDataModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.ModelDataPresenter;

/**
 * Исполняемый шаг скрипта, применяющий все изменения в игре, которые были произведены в рамках одной фазы
 */
public class ApplyPhaseChanges implements UIRunnableScript<EmptyScriptState> {
    private final ModelDataPresenter modelDataPresenter;
    private final MapFillingPresenter mapFillingPresenter;

    public ApplyPhaseChanges(PresentingGameState gameState) {
        this.modelDataPresenter = gameState.getModulePresenter(ModelDataModule.NAME);
        this.mapFillingPresenter = gameState.getModulePresenter(MapFillingModule.NAME);
    }

    @Override
    public void handle(UIScriptElementContext context, EmptyScriptState state) {
        modelDataPresenter.updateToNextPhase();
        mapFillingPresenter.updateToNextPhase();
    }
}
