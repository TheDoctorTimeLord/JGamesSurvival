package com.jgames.survival.presenter.core.uiscripts.contextes;

import com.badlogic.gdx.utils.Null;
import com.jgames.survival.presenter.core.UIAction;

public interface UIScriptElementContext {
    @Null UIAction getDispatchedAction();
}
