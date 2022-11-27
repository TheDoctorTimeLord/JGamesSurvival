package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignBottomLeft extends AlignConstraint {
    public static final String NAME = "alignBottomLeft";

    public AlignBottomLeft() {
        super(NAME, -1, -1, 1, 1);
    }
}
