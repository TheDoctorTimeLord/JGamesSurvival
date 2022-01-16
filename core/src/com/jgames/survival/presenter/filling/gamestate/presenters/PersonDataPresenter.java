package com.jgames.survival.presenter.filling.gamestate.presenters;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Point;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.modules.PersonData;

public interface PersonDataPresenter extends ModulePresenter {
    List<PersonData> getDataForAllPersons();
    PersonData getCurrentPersonState(int personId);
    void updateToNextPhase();
    boolean isLastPhase();
    @Nullable
    Integer getPersonOnPosition(Point position);
}
