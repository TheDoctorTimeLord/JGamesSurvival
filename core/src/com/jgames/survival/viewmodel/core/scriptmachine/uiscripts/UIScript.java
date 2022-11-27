package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts;

import ru.jengine.utils.Logger;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;

public interface UIScript extends UIScriptHandler {
    String getScriptName();
    @Null Class<? extends UIAction> getWaitedAction();
    void resetScript();

    void setLogger(Logger logger);
}
