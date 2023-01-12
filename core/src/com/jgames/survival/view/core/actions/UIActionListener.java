package com.jgames.survival.view.core.actions;

import com.jgames.survival.utils.pubsub.Subscriber;
import com.jgames.survival.view.core.CanBeActor;

public abstract class UIActionListener implements Subscriber<UIAction> {
    private final CanBeActor ownerListener;

    protected UIActionListener(CanBeActor ownerListener) {
        this.ownerListener = ownerListener;
    }

    @Override
    public boolean needNotify(UIAction information) {
        return ownerListener.equals(information.getReceiver());
    }
}
