package com.jgames.survival.presenter.filling.clickactions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.presenter.core.UIActionDispatcher;

public class ButtonClickedHandler extends ClickListener {
    private final UIActionDispatcher actionDispatcher;

    public ButtonClickedHandler(UIActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        ButtonClicked action = Pools.obtain(ButtonClicked.class);
        action.setClickedButton((TextButton)event.getListenerActor());

        actionDispatcher.dispatch(action, Pools::free);
    }
}
