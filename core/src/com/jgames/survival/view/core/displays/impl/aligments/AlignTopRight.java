package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignTopRight extends AlignConstraint {
    public static final String NAME = "alignTopRight";

    public AlignTopRight() {
        super(NAME, 1, 1, -1, -1);
    }
}
