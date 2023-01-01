package com.jgames.survival.ui.uifactories.config;

import com.badlogic.gdx.utils.Align;

public class PhaseAndTurnConfig {
    private int align = Align.bottomRight;
    private int buttonMiddleWidth = 120;
    private int buttonMiddleHeight = 50;

    private String nextPhaseName = "Next Phase";
    private String nextTurnName = "Next Turn";

    public int getAlign() {
        return align;
    }

    public int getButtonMiddleHeight() {
        return buttonMiddleHeight;
    }

    public int getButtonMiddleWidth() {
        return buttonMiddleWidth;
    }

    public String getNextPhaseName() {
        return nextPhaseName;
    }

    public String getNextTurnName() {
        return nextTurnName;
    }
}
