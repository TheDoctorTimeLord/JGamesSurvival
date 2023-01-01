package com.jgames.survival.ui.uifactories.config;

import com.badlogic.gdx.utils.Align;

public class SaveAndLoadConfig {
    private int align = Align.topRight;
    private int buttonMiddleWidth = 120;
    private int buttonMiddleHeight = 50;

    private String saveName = "Save";
    private String loadName = "Load";

    private String saveFileName = "save.json";

    public int getAlign() {
        return align;
    }

    public int getButtonMiddleHeight() {
        return buttonMiddleHeight;
    }

    public int getButtonMiddleWidth() {
        return buttonMiddleWidth;
    }

    public String getSaveName() {
        return saveName;
    }

    public String getLoadName() {
        return loadName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }
}
