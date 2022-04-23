package com.jgames.survival.presenter.filling.clickactionhandlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.presenter.core.UIActionDispatcher;
import com.jgames.survival.presenter.filling.clickactions.CommandButtonClicked;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.CommandButton.ClickOnCommandButton;

/**
 * Обработчик системного события нажатия на кнопку {@link com.jgames.survival.ui.uifactories.CommandPanelFactory
 * команды}. Уведомляет систему скриптов о том, что кнопка команды была нажата.
 */
public class CommandButtonClickHandler implements ClickOnCommandButton {
    private final UIActionDispatcher actionDispatcher;

    public CommandButtonClickHandler(UIActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    public void clicked(InputEvent event, CommandButton commandButton) {
        CommandButtonClicked action = Pools.obtain(CommandButtonClicked.class);
        action.setCommandButton(commandButton);

        actionDispatcher.dispatch(action, Pools::free);
    }
}
