package com.jgames.survival.control.clickhandlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.control.UIActionDispatcher;
import com.jgames.survival.control.actions.PhaseOrTurnClicked;

public class PhaseOrTurnClickedHandler extends ClickListener { //TODO вынести общую часть хендлеров в общий класс
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
