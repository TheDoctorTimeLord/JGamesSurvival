package com.jgames.survival.viewmodel.impl.viewstate.changequeue;

import java.util.ArrayDeque;
import java.util.Queue;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.jgames.survival.viewmodel.core.viewstate.ModulePresenter;
import com.jgames.survival.viewmodel.core.viewstate.ViewStateModule;

@Bean
public class ViewChangesQueueModule implements ViewStateModule<ModulePresenter>, ModulePresenter {
    public static final String NAME = "viewChangesQueue";
    private static final ViewChange END_PHASE_MARK = () -> {};

    private final Queue<ViewChange> viewChangesQueue = new ArrayDeque<>();
    private final Logger logger;

    public ViewChangesQueueModule(Logger logger) {
        this.logger = logger;
    }

    public void registerViewChange(ViewChange change) {
        viewChangesQueue.add(change);
    }

    public void markEndPhase() {
        viewChangesQueue.add(END_PHASE_MARK);
    }

    /**
     * Выполняет очередное изменение отображаемого состояния. Возвращает true - если в рамках текущей фазы боя ещё
     * есть невыполненные изменения, false - в противном случае
     */
    public boolean applyNextViewChange() {
        if (viewChangesQueue.isEmpty()) {
            return false;
        }

        ViewChange viewChange = viewChangesQueue.poll();

        try {
            viewChange.applyChange();
        } catch (Exception e) {
            logger.error("ViewChangesQueueModule", "Error when apply changes", e);
        }

        return !END_PHASE_MARK.equals(viewChange) && !viewChangesQueue.isEmpty();
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public ModulePresenter getPresenter() {
        return this;
    }
}
