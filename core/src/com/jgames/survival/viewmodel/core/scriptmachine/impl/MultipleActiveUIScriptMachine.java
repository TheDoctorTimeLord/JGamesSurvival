package com.jgames.survival.viewmodel.core.scriptmachine.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.CollectionUtils;
import ru.jengine.utils.Logger;

import com.jgames.survival.view.core.UIRuntimeException;
import com.jgames.survival.viewmodel.core.UpdatableOnGameTick;
import com.jgames.survival.viewmodel.core.scriptmachine.DispatcherUIScriptMachine;
import com.jgames.survival.viewmodel.core.scriptmachine.UIAction;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.UIScript;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContext;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.contextes.UIScriptContextImpl;

@Bean
public class MultipleActiveUIScriptMachine implements DispatcherUIScriptMachine, UpdatableOnGameTick {
    private final Map<String, UIScript> registeredScripts = new ConcurrentHashMap<>();
    private final List<UIScript> notWaitedScripts = new CopyOnWriteArrayList<>();
    private final Map<Class<? extends UIAction>, List<UIScript>> waitedScripts = new ConcurrentHashMap<>();
    private final Logger logger;

    private volatile Queue<UIActionData<?>> notHandledUIActions = new ArrayDeque<>();
    private volatile Queue<UIActionData<?>> handlingUIActions = new ArrayDeque<>();

    public MultipleActiveUIScriptMachine(Logger logger) {
        this.logger = logger;
    }

    @Override
    public <T extends UIAction> void dispatch(T action, CallbackAfterDispatch<T> callback) {
        notHandledUIActions.add(new UIActionData<>(action, callback));
    }

    @Override
    public void update() {
        swapActionsQueue();
        handleNotWaitedScripts();

        while (!handlingUIActions.isEmpty()) {
            //Получаем очередное действие для обработки
            UIActionData<?> uiActionData = handlingUIActions.remove();

            UIAction uiAction = uiActionData.uiAction;
            Class<? extends UIAction> handledActionClass = uiAction.getClass();
            UIScriptContext uiScriptContext = new UIScriptContextImpl(this, uiAction);

            //Собираем все активные скрипты, которые могут быть выполнены. Все остальные активные скрипты нужно
            // попытаться откатить, если action будет обработан. Для оптимизации действий запишем все скрипты на
            // откат, а потом удалим тот, который отработал
            Set<UIScript> needRollbackScripts = new HashSet<>();
            List<UIScript> handlingScripts = new ArrayList<>();
            waitedScripts.forEach((actionClass, waitedScripts) -> {
                if (actionClass.isAssignableFrom(handledActionClass)) {
                    handlingScripts.addAll(waitedScripts);
                }
                needRollbackScripts.addAll(waitedScripts);
            });

            Map<Class<? extends UIAction>, List<UIScript>> refreshWaitedActionScripts = new HashMap<>();

            for (UIScript handlingScript : handlingScripts) {
                //Если до этого action не был никем обработан и, если текущий скрипт может его обработать, обрабатываем
                // action. Скорее всего после этого тип ожидаемого скриптом action изменится, поэтому помещаем этот
                // скрипт на обновление ожидаемого action.
                if (handlingScript.isValid(uiAction)) {
                    refreshWaitedActionScripts.compute(handlingScript.getWaitedAction(), (a, l) -> CollectionUtils.concat(handlingScript));
                    handlingScript.handle(uiScriptContext);
                    break;
                }
            }
            //Если нашёлся скрипт, который обработал action, то все остальные скрипты нужно откатить в своём состоянии.
            // Чтобы откатить все не отработавшие handlingScripts, исключаем из них отработавший скрипт и добавляем их
            // в needRollbackScript
            if (!refreshWaitedActionScripts.isEmpty()) {
                needRollbackScripts.remove(refreshWaitedActionScripts.values().iterator().next().get(0));

                for (UIScript needRollbackScript : needRollbackScripts) {
                    Class<? extends UIAction> waitedAction = needRollbackScript.getWaitedAction();
                    if (needRollbackScript.rollback(uiAction)) {
                        refreshWaitedActionScripts.compute(waitedAction, (a, l) -> {
                            if (l == null) {
                                l = new ArrayList<>();
                            }
                            l.add(needRollbackScript);
                            return l;
                        });
                    }
                }
            }

            //Удаляем все обновляемые скрипты из списка ожидающих действий
            for (Entry<Class<? extends UIAction>, List<UIScript>> entry : refreshWaitedActionScripts.entrySet()) {
                waitedScripts.computeIfPresent(entry.getKey(), (a, waitedScripts) -> {
                    waitedScripts.removeAll(entry.getValue());
                    return waitedScripts;
                });
            }

            //Так как notWaitedScripts - CopyOnWriteArrayList, лучше минимизировать количество записей (тут будет одна)
            notWaitedScripts.addAll(refreshWaitedActionScripts.values().stream()
                    .flatMap(Collection::stream)
                    .toList());
        }
    }

    @Override
    public void registerScript(UIScript script) {
        String scriptName = script.getScriptName();
        if (registeredScripts.containsKey(scriptName)) {
            throw new UIRuntimeException("UI script with name [%s] was registered".formatted(scriptName));
        }
        script.setLogger(logger);
        registeredScripts.put(scriptName, script);
        notWaitedScripts.add(script);
    }

    @Override
    public void deleteScript(String scriptName) {
        registeredScripts.remove(scriptName);
    }

    private void swapActionsQueue() {
        Queue<UIActionData<?>> notHandledActions = notHandledUIActions;
        notHandledUIActions = handlingUIActions;
        handlingUIActions = notHandledActions;
    }

    private void handleNotWaitedScripts() {
        if (!notWaitedScripts.isEmpty()) {
            notWaitedScripts.forEach(this::waitAction);
            notWaitedScripts.clear();
        }
    }

    private void waitAction(UIScript script) {
        Class<? extends UIAction> waitedAction = script.getWaitedAction();
        if (waitedAction != null) {
            waitedScripts.compute(waitedAction, (actionClass, waitedScripts) -> {
                List<UIScript> scripts = waitedScripts != null ? waitedScripts : new ArrayList<>();
                scripts.add(script);
                return scripts;
            });
        }
    }

    private record UIActionData<T extends UIAction>(T uiAction, CallbackAfterDispatch<T> callback) {}
}
