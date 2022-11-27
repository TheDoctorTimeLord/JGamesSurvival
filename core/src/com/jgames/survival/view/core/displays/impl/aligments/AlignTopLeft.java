package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignTopLeft extends AlignConstraint {
    public static final String NAME = "alignTopLeft";

    public AlignTopLeft() {
        super(NAME, -1, 1, 1, -1);
    }
}
