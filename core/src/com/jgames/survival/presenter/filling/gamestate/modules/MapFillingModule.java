package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.serviceclasses.PointPool;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.CollectionUtils;
import ru.jengine.utils.Pair;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;

@Bean
public class MapFillingModule implements PresentingStateModule<MapFillingModule>, MapFillingPresenter, ResettableModule {
    public static final String NAME = "mapFilling";
    private final PhasedField<Set<Point>> updatedCells = new PhasedField<>(HashSet::new, s -> new HashSet<>(), true);
    private final PhasedFieldWithMap<Point, List<Integer>> objectsOnCell = new PhasedFieldWithMap<>(
            point -> new ArrayList<>(),
            ArrayList::new
    );
    private final Set<Point> battlefieldCells = new HashSet<>();

    public MapFillingModule() {
        addState();
    }

    public void addState() {
        updatedCells.duplicateLastState();
    }

    public void addMapCellItem(Point mapCellItem) {
        battlefieldCells.add(mapCellItem);
    }

    public void markCellAsUpdated(Point cellCoordinate) {
        updatedCells.getOrCreateLastState().add(cellCoordinate);
    }

    public void addObjectsOnCell(Point point, int objectId) {
        objectsOnCell.getOrCreateLastState(point).add(objectId);
    }

    @Override
    public void reset() {
        updatedCells.reset();
        updatedCells.duplicateLastState(); //TODO ЧЁ КОГО??
        objectsOnCell.reset();

        battlefieldCells.clear();
    }

    public void updateObjectsOnCell(Integer objectId, Point lastPosition, Point newPosition) {
        List<Integer> objectsOnPosition = objectsOnCell.getLastState(lastPosition);
        if (objectsOnPosition != null) {
            objectsOnPosition.remove(objectId);

            if (objectsOnPosition.isEmpty()) {
                objectsOnCell.removeFromLastState(lastPosition);
            }
        }

        objectsOnCell.getOrCreateLastState(newPosition).add(objectId);
    }

    @Override
    public void updateToNextPhase() {
        updatedCells.updateToNextPhase();
        objectsOnCell.updateToNextPhase();
    }

    @Override
    public Set<Point> getBattlefieldCells() {
        return new HashSet<>(battlefieldCells);
    }

    @Override
    public Pair<Point, Point> getBattlefieldRectangleCoordinate() {
        int bottomX = Integer.MAX_VALUE;
        int leftY = Integer.MAX_VALUE;
        int topX = Integer.MIN_VALUE;
        int rightY = Integer.MIN_VALUE;

        for (Point battlefieldCell : battlefieldCells) {
            int x = battlefieldCell.getX();
            int y = battlefieldCell.getY();

            if (x < bottomX) {
                bottomX = x;
            }
            if (x > topX) {
                topX = x;
            }
            if (y < leftY) {
                leftY = y;
            }
            if (y > rightY) {
                rightY = y;
            }
        }

        return battlefieldCells.isEmpty()
                ? new Pair<>(PointPool.obtain(0, 0), PointPool.obtain(0, 0))
                : new Pair<>(PointPool.obtain(bottomX, leftY), PointPool.obtain(topX, rightY));
    }

    @Override
    public Collection<Point> getUpdatedCells() {
        return updatedCells.isLastPhase() ? Collections.emptySet() : new HashSet<>(updatedCells.getCurrentState());
    }

    @Override
    public List<Integer> getIdsOnCell(Point point) {
        return (List<Integer>)CollectionUtils.getOrEmpty(objectsOnCell.getCurrentState(point));
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public MapFillingModule getPresenter() {
        return this;
    }
}
