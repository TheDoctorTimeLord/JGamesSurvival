package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignBottom extends AlignConstraint {
    public static final String NAME = "alignBottom";

    public AlignBottom() {
        super(NAME, 0, -1, 0, 1);
    }
}
