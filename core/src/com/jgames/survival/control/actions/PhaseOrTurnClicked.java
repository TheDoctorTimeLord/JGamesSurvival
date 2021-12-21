package com.jgames.survival.control.actions;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.jgames.survival.control.UIAction;

public class PhaseOrTurnClicked implements UIAction, Poolable {
    private TextButton clickedButton;

    @Override
    public void reset() {
        clickedButton = null;
    }

    public TextButton getClickedButton() {
        return clickedButton;
    }

    public void setClickedButton(TextButton clickedCell) {
        this.clickedButton = clickedCell;
    }
}
