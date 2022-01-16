package com.jgames.survival.presenter.filling.clickhandlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.presenter.core.UIActionDispatcher;
import com.jgames.survival.presenter.filling.actions.PhaseOrTurnClicked;

public class PhaseOrTurnClickedHandler extends ClickListener {
    private final UIActionDispatcher actionDispatcher;

    public PhaseOrTurnClickedHandler(UIActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        PhaseOrTurnClicked action = Pools.obtain(PhaseOrTurnClicked.class);
        action.setClickedButton((TextButton)event.getListenerActor());

        actionDispatcher.dispatch(action, Pools::free);
    }
}
