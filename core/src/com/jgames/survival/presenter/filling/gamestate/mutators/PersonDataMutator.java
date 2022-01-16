package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.Arrays;
import java.util.List;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonData;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonDataModule;
import com.jgames.survival.presenter.filling.gamestate.modules.UpdatedCellsModule;
import com.jgames.survival.utils.MoveUtils;

public class PersonDataMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = Arrays.asList(PersonDataModule.NAME, UpdatedCellsModule.NAME);

    private PersonDataModule personData;
    private UpdatedCellsModule updatedCells;

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        personData = (PersonDataModule)modules[0];
        updatedCells = (UpdatedCellsModule)modules[1];
    }

    public void addPerson(int personId, Point startPosition, Direction direction) {
        personData.addPerson(personId, startPosition, direction);
    }

    public void movePerson(int personId, Point newPosition) {
        Point lastPosition = personData.getLastPersonState(personId).getPosition();
        PersonData data = personData.getLastPersonState(personId);
        data.setDirection(MoveUtils.getRotate(lastPosition, newPosition));
        data.setPosition(newPosition);

        updatedCells.markCellAsUpdated(lastPosition);
        updatedCells.markCellAsUpdated(newPosition);
    }

    public void rotatePerson(int personId, Direction newDirection) {
        PersonData personState = personData.getLastPersonState(personId);
        personState.setDirection(newDirection);

        updatedCells.markCellAsUpdated(personState.getPosition());
    }

    public void damagePerson(int personId, int damagePoints) {
        PersonData personState = personData.getLastPersonState(personId);
        personState.damage(damagePoints);

        updatedCells.markCellAsUpdated(personState.getPosition());
    }

    public void startNewPhase() {
        personData.addState();
        updatedCells.addState();
    }
}
