package com.jgames.survival.model.api.interaction;

import com.jgames.survival.utils.pubsub.Subscriber;

/**
 * Слушатель всех произошедших изменений в игре. Получает информацию о всех произошедших изменениях. Данный интерфейс
 * служит для того, чтобы единоразово уточнить ожидаемый тип изменений у класса {@link Subscriber}
 */
public interface GameChangeListener extends Subscriber<GameChange> {
}
