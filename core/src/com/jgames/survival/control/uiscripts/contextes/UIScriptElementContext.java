package com.jgames.survival.control.uiscripts.contextes;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.control.UIAction;

public interface UIScriptElementContext {
    @Null UIAction getDispatchedAction();
    void resetActiveScript(); //TODO ИЗБАВСЯ ОТ ЭТОГО!
}
