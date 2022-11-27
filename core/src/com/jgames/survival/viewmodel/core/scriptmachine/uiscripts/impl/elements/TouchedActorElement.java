package com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.elements;

import java.util.function.Function;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.UIActionDispatcher;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContext;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.UIScriptElement;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.UIScriptState;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.actions.TouchedActorAction;

public class TouchedActorElement implements UIScriptElement {
    public static final Function<UIActionDispatcher, ChangeListener> TOUCHED_ACTOR_LISTENER =
            dispatcher -> new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    TouchedActorAction action = Pools.obtain(TouchedActorAction.class);
                    action.setTouchedActor(event.getListenerActor());

                    dispatcher.dispatch(action, Pools::free);
                }
            };

    private final Actor targetActor;
    private final String actorVariableKey;

    public TouchedActorElement(Actor targetActor, String actorVariableKey) {
        this.targetActor = targetActor;
        this.actorVariableKey = actorVariableKey;
    }

    @Override
    public Class<? extends UIAction> getActivationAction() {
        return TouchedActorAction.class;
    }

    @Override
    public boolean isValid(UIAction action) {
        return ((TouchedActorAction)action).getTouchedActor().equals(targetActor);
    }

    @Override
    public void handle(UIScriptContext context, UIScriptState state) {
        state.put(actorVariableKey, targetActor);
    }

    @Override
    public boolean rollback(UIAction action, UIScriptState state) {
        state.remove(actorVariableKey);
        return true;
    }
}
