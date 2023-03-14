package com.jgames.survival.model.game.logic.ai;

import com.jgames.survival.model.game.logic.ai.cognition.CognitionCluster;

public interface ToCognitionConverter {
    void convert(ToCognitionBaseConvertable convertable, CognitionCluster storage);
}
