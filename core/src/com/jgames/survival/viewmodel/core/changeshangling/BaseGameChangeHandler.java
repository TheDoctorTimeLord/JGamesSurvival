package com.jgames.survival.viewmodel.core.changeshangling;

import javax.annotation.Nullable;

import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.viewmodel.core.viewstate.PresentingViewState;

/**
 * Абстрактный класс всех произошедших изменений в игре. Наследник этого класса является слушателем всех изменений в
 * игре. Позволяет обработать и сохранить информацию, которая необходима для отображения пользователю на экране.
 */
public abstract class BaseGameChangeHandler implements GameChangeHandler {
    private final GameChangeApplier gameChangeApplier;

    protected BaseGameChangeHandler(GameChangeApplier gameChangeApplier) {
        this.gameChangeApplier = gameChangeApplier;
    }

    @Override
    public void notify(GameChange information) {
        gameChangeApplier.apply(createApplyingTask(information));
    }

    @Nullable
    protected abstract Runnable createApplyingTask(GameChange information);

    /**
     * Сохраняет текущее состояние пользовательского интерфейса, которое в последствии будет изменено этим обработчиком
     * @param presentingGameState информация о текущем состоянии игры, которая будет отображена пользователю
     */
    public abstract void setGameState(PresentingViewState presentingGameState);
}
