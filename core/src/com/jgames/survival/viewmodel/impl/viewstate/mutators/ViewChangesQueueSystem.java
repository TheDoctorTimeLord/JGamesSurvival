package com.jgames.survival.viewmodel.impl.viewstate.mutators;

import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.viewmodel.core.viewstate.ModuleMutator;
import com.jgames.survival.viewmodel.core.viewstate.ViewStateModule;
import com.jgames.survival.viewmodel.impl.viewstate.changequeue.ViewChange;
import com.jgames.survival.viewmodel.impl.viewstate.changequeue.ViewChangesQueueModule;

@Bean
public class ViewChangesQueueSystem implements ModuleMutator {
    private ViewChangesQueueModule viewChangesModule;

    @Override
    public List<String> getUsedModuleNames() {
        return List.of(ViewChangesQueueModule.NAME);
    }

    @Override
    public void connectWithModule(ViewStateModule<?>... modules) {
        viewChangesModule = (ViewChangesQueueModule)modules[0];
    }

    public void registerViewChange(ViewChange change) {
        viewChangesModule.registerViewChange(change);
    }

    public void markEndPhase() {
        viewChangesModule.markEndPhase();
    }

    /**
     * Выполняет очередное изменение отображаемого состояния. Возвращает true - если в рамках текущей фазы боя ещё
     * есть невыполненные изменения, false - в противном случае
     */
    public boolean applyNextViewChange() {
        return viewChangesModule.applyNextViewChange();
    }
}
