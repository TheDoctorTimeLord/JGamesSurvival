package com.jgames.survival.presenter.core.changeshangling;

import com.jgames.survival.model.api.interaction.GameChangeListener;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;

/**
 * Обработчик всех произошедших изменений в игре. Класс, который имлементировал данный интерфейс, является
 * слушателем всех изменений в игре. Позволяет обработать и сохранить информацию, которая необходима для отображения
 * пользователю на экране.
 */
public interface GameChangeHandler extends GameChangeListener {
    /**
     * Сохраняет текущее состояние пользовательского интерфейса, которое в последствии будет изменено этим обработчиком
     * @param presentingGameState информация о текущем состоянии игры, которая будет отображена пользователю
     */
    void setGameState(PresentingGameState presentingGameState);
}
