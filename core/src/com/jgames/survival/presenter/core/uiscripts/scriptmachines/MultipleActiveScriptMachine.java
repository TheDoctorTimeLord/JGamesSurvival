package com.jgames.survival.presenter.core.uiscripts.scriptmachines;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.core.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptContextImpl;
import com.jgames.survival.presenter.core.uiscripts.sctipts.UIScript;
import com.jgames.survival.utils.PoolableDeque;
import com.jgames.survival.utils.PoolableLinkedDeque;

public class MultipleActiveScriptMachine implements DispatcherUIScriptMachine {
    private final Map<Class<? extends UIAction>, PoolableDeque<ScriptEnvironment>> listeners =
            new ConcurrentHashMap<>();
    private final List<ScriptEnvironment> availableScripts = new ArrayList<>();

    @Override
    public <T extends UIAction> void dispatch(T action, CallbackAfterDispatch<T> callback) {
        AtomicReference<ScriptEnvironment> handledEnvironment = new AtomicReference<>();

        listeners.computeIfPresent(action.getClass(), (aCls, scriptEnvironments) -> {
            //TODO могут возникнуть проблемы с долгими скриптами и полученными action во время исполнения
            for (ScriptEnvironment environment : scriptEnvironments) {
                if (environment.needDeleteListeners == null && environment.script.isValid(action)) {
                    environment.needDeleteListeners = environment.script.getWaitedActions();
                    environment.script.handle(new UIScriptContextImpl(this).setDispatchedAction(action));
                    handledEnvironment.set(environment);
                    break;
                }
            }
            return scriptEnvironments;
        });

        ScriptEnvironment environment = handledEnvironment.get();
        if (environment != null) {
            changeListeners(environment, action, true);
        }

        callback.run(action);
    }

    private <T extends UIAction> void changeListeners(ScriptEnvironment environment, T action, boolean needRollbackOther) {
        removeAllOldListeners(environment);
        addScriptToListeners(environment);

        if (needRollbackOther && action != null) {
            rollbackActiveScripts(action, environment);
        }

        environment.needDeleteListeners = null;
    }

    private <T extends UIAction> void rollbackActiveScripts(T action, ScriptEnvironment handledScript) {
        synchronized (availableScripts) {
            availableScripts.stream()
                    .filter(env -> !handledScript.equals(env))
                    .filter(env -> env.script.isActive())
                    .forEach(env -> {
                        env.needDeleteListeners = env.script.getWaitedActions();
                        if (env.script.rollback(action)) {
                            changeListeners(env, action, false);
                        }
                    });
        }
    }

    private void removeAllOldListeners(ScriptEnvironment environment) {
        environment.needDeleteListeners.forEach(aCls -> listeners.computeIfPresent(aCls, (k, v) -> {
            v.remove(environment);
            return v;
        }));
    }

    @Override
    public void registerScript(UIScript script) {
        ScriptEnvironment wrappedScript = new ScriptEnvironment(script);
        synchronized (availableScripts) {
            availableScripts.add(wrappedScript);
        }
        addScriptToListeners(wrappedScript);
    }

    @Override
    public void deleteScript(String scriptName) {
        synchronized (availableScripts) {
            ScriptEnvironment scriptEnvironment = availableScripts.stream()
                    .filter(env -> env.script.getScriptName().equals(scriptName))
                    .findFirst()
                    .orElse(null);
            availableScripts.remove(scriptEnvironment);
        }
    }

    private void addScriptToListeners(ScriptEnvironment environment) {
        Set<Class<? extends UIAction>> waitedActions = environment.script.getWaitedActions();
        waitedActions.forEach(actionClass -> addScriptToListeners(environment, actionClass));
    }

    private void addScriptToListeners(ScriptEnvironment script, Class<? extends UIAction> listenedAction) {
        listeners.merge(listenedAction, getPoolableDequeWithSingleValue(script), (oldValue, newValue) -> {
            for (ScriptEnvironment environment : newValue) {
                if (environment.script.isActive()) {
                    oldValue.addFirst(environment);
                } else {
                    oldValue.addLast(environment);
                }
            }
            Pools.free(newValue);
            return oldValue;
        });
    }

    @SuppressWarnings("unchecked")
    private static PoolableDeque<ScriptEnvironment> getPoolableDequeWithSingleValue(ScriptEnvironment singleValue)
    {
        return ((PoolableLinkedDeque<ScriptEnvironment>)Pools.obtain(PoolableLinkedDeque.class))
                .addWithChain(singleValue);
    }

    private static class ScriptEnvironment {
        private final UIScript script;
        private Set<Class<? extends UIAction>> needDeleteListeners = null;

        private ScriptEnvironment(UIScript script) {
            this.script = script;
        }
    }

}
