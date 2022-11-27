package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignLeft extends AlignConstraint {
    public static final String NAME = "alignLeft";

    public AlignLeft() {
        super(NAME, -1, 0, 1, 0);
    }
}
