package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Function;
import java.util.function.Supplier;

public class PhasedField<D> {
    private final Deque<D> states = new ConcurrentLinkedDeque<>();
    private final Supplier<D> dataGenerator;
    private final Function<D, D> dataDuplicator;
    private final int minSize;

    public PhasedField(Supplier<D> dataGenerator, Function<D, D> dataDuplicator) {
        this(dataGenerator, dataDuplicator, false);
    }

    public PhasedField(Supplier<D> dataGenerator, Function<D, D> dataDuplicator, boolean canBeEmpty) {
        this.dataGenerator = dataGenerator;
        this.dataDuplicator = dataDuplicator;
        this.minSize = canBeEmpty ? 0 : 1;

        if (!canBeEmpty) {
            duplicateLastState();
        }
    }

    public D getCurrentState() {
        return states.getFirst();
    }

    public D getLastState() {
        return states.getLast();
    }

    public D getOrCreateLastState() {
        return states.isEmpty() ? dataGenerator.get() : states.getLast();
    }

    public void duplicateLastState() {
        if (states.isEmpty()) {
            states.addLast(dataGenerator.get());
            return;
        }

        states.addLast(dataDuplicator.apply(getLastState()));
    }

    public void updateToNextPhase() {
        if (states.size() > minSize) {
            states.poll();
        }
    }

    public boolean isLastPhase() {
        return states.size() <= minSize;
    }
}
