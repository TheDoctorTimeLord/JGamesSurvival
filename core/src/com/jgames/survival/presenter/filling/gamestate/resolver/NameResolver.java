package com.jgames.survival.presenter.filling.gamestate.resolver;

import java.awt.*;
import java.util.Collection;

public interface NameResolver {
    Collection<String> resolveCell(Point point);
}
