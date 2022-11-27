package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;

public class TouchedActorAction implements UIAction, Poolable {
    private Actor touchedActor;

    public void setTouchedActor(Actor touchedActor) {
        this.touchedActor = touchedActor;
    }

    public Actor getTouchedActor() {
        return touchedActor;
    }

    @Override
    public void reset() {
        touchedActor = null;
    }
}
