package com.jgames.survival.view.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.jgames.survival.view.core.Bindable;
import com.jgames.survival.view.core.CanBeActor;
import com.jgames.survival.view.core.HasName;
import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.displays.impl.RootDisplay;

public class UIComponentsFinder {
    @Nullable
    public static CanBeActor findByPath(UIManagementSystem uiManagementSystem, String... path) {
        return uiManagementSystem.findByPath(path);
    }

    public static String[] getComponentPath(Bindable component) {
        List<String> path = new ArrayList<>();

        Display parent = component.getParent();
        String parentName;
        while (parent != null && !RootDisplay.NAME.equals(parentName = parent.getName())) {
            path.add(parentName);
            parent = parent.getParent();
        }

        int size = path.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[size - i - 1] = path.get(i);
        }

        return result;
    }

    public static <T extends Bindable & HasName> String[] getComponentFullPath(T component) {
        String[] path = getComponentPath(component);
        String[] fullPath = Arrays.copyOf(path, path.length + 1);
        fullPath[path.length] = component.getName();
        return fullPath;
    }
}
