package com.jgames.survival.presenter.core.gamestate;

public interface PresentingStateModule<P extends ModulePresenter> {
    String getModuleName();
    P getPresenter();
}
