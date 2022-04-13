package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.PersonDataPresenter;

public class PersonDataModule implements PresentingStateModule<PersonDataPresenter>, PersonDataPresenter {
    public static final String NAME = "personData";
    private static final int STARTED_HP = 10;

    private final Map<Integer, Deque<PersonData>> personsDataStates = new HashMap<>();
    private final Map<Point, Integer> currentPersonsPosition = new HashMap<>();

    public synchronized void addPerson(int personId, Point startedPosition, Direction direction) {
        personsDataStates.put(personId, new ArrayDeque<>());
        personsDataStates.get(personId).add(new PersonData(personId, STARTED_HP, startedPosition, direction));
        currentPersonsPosition.put(startedPosition, personId);
    }

    public synchronized PersonData getLastPersonState(int personId) {
        return personsDataStates.get(personId).getLast();
    }

    public synchronized void addState() {
        personsDataStates.forEach((id, states) -> states.addLast(new PersonData(states.getLast())));
    }

    @Override
    public synchronized List<PersonData> getDataForAllPersons() {
        return personsDataStates.values().stream()
                .map(Deque::getFirst)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized PersonData getCurrentPersonState(int personId) {
        return personsDataStates.get(personId).getFirst();
    }

    @Override
    public synchronized void updateToNextPhase() {
        personsDataStates.values().stream()
                .map(Deque::getFirst)
                .filter(personData -> !personData.isKilled())
                .forEach(personData -> currentPersonsPosition.remove(personData.getPosition()));
        personsDataStates.forEach((id, states) -> {
            if (states.size() > 1) {
                states.removeFirst();
            }
        });
        personsDataStates.values().stream()
                .map(Deque::getFirst)
                .filter(PersonData::isKilled)
                .filter(personData -> currentPersonsPosition.containsKey(personData.getPosition()))
                .forEach(personData -> currentPersonsPosition.put(personData.getPosition(), personData.getId()));
    }

    @Override
    public synchronized boolean isLastPhase() {
        return personsDataStates.values().iterator().next().size() == 1;
    }

    @Override
    @Nullable
    public synchronized Integer getPersonOnPosition(Point position) {
        return currentPersonsPosition.get(position);
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public PersonDataPresenter getPresenter() {
        return this;
    }
}
