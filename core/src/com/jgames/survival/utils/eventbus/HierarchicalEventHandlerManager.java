package com.jgames.survival.utils.eventbus;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.eventqueue.dataclasses.EventHandlersManager;
import ru.jengine.eventqueue.event.Event;
import ru.jengine.eventqueue.event.PostHandler;
import ru.jengine.eventqueue.event.PreHandler;
import ru.jengine.utils.HasPriority;
import ru.jengine.utils.SortedMultiset;

public class HierarchicalEventHandlerManager extends EventHandlersManager {
    public HierarchicalEventHandlerManager(List<PreHandler<?>> preHandlers) {
        super(preHandlers);
    }

    @Override
    public List<PreHandler<Event>> getPreHandlers(Event event) {
        return extractHierarchy(event).stream()
                .map(super::getPreHandlers)
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public SortedMultiset<PostHandler<Event>> getPostHandlers(Event event) {
        return extractHierarchy(event).stream()
                .map(super::getPostHandlers)
                .collect(
                        () -> new SortedMultiset<>(HasPriority::getPriority),
                        SortedMultiset::addAll,
                        SortedMultiset::addAll
                );
    }

    private static List<Class<?>> extractHierarchy(Event event) {
        List<Class<?>> hierarchy = new ArrayList<>();

        Class<?> currentClass = event.getClass();
        hierarchy.add(currentClass);

        while (!isRootEventClass(currentClass)) {
            currentClass = currentClass.getSuperclass();
            hierarchy.add(currentClass);
        }

        return hierarchy;
    }

    private static boolean isRootEventClass(Class<?> eventClass) {
        for (Class<?> implementedInterface : eventClass.getInterfaces()) {
            if (implementedInterface.equals(Event.class)) {
                return true;
            }
        }
        return false;
    }
}
