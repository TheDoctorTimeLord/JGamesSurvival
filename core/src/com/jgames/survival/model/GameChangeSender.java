package com.jgames.survival.model;

import com.jgames.survival.model.api.interaction.GameChange;

public interface GameChangeSender {
    void sendGameChange(GameChange gameChange);
}
