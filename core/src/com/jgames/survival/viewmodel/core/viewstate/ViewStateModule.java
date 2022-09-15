package com.jgames.survival.viewmodel.core.viewstate;

public interface ViewStateModule<P extends ModulePresenter> {
    String getModuleName();
    P getPresenter();
}
