package com.jgames.survival.model.game.presentation;

import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.game.logic.GameBattleHandler;

/**
 * Интерфейс, описывающий маппер игровых событий в специальное представление, подходящее для хранения данных в UI.
 * Классы, имплементирующие этот интерфейс, представляют собой слой абстракции между игровой логикой и UI. Эти классы
 * занимаются подготовкой произошедших в игре действий к дальнейшей обработке для показа пользователю
 */
public interface ToGameChangeMapper {
    /**
     * Метод настройки маппера. Позволяет мапперу подписаться на нужные игровые действия и сохранить класс, через
     * который будут отправлены все полученные действия
     * @param changeSender класс, через который будут отправлены все полученные действия
     * @param battleHandler общий обработчик игры, через который можно подписаться на действия, исполненные в игре
     */
    void configureMapper(GameChangeSender changeSender, GameBattleHandler battleHandler);
}
