package com.jgames.survival.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class TextInformation extends Table { //TODO ПЕРЕДЕЛАТЬ, СУПЕР КОСТЫЛЬНЫЙ КЛАСС
    private static final LabelStyle LABEL_STYLE;
    private volatile ScrollPane scrollPane;

    static {
        LABEL_STYLE = new LabelStyle();
        LABEL_STYLE.font = new BitmapFont();
        LABEL_STYLE.fontColor = new Color(0.5f, 0.5f, 0.5f, 1);
    }

    public TextInformation() {
        setFillParent(true);
        setColor(0.5f, 0.5f, 0.5f, 1);
        align(Align.center | Align.top);
    }

    public void addText(String text) {
        add(new Label(text, LABEL_STYLE)).pad(4);
        row();
    }

    public ScrollPane getScrollPaneWrapper(float width, float height) {
        if (scrollPane == null) {
            synchronized (this) {
                if (scrollPane == null) {
                    scrollPane = new ScrollPane(this);
                    scrollPane.setSmoothScrolling(false);
                    scrollPane.setBounds(0, 0, width, height);
                    scrollPane.setTransform(true);
                }
            }
        }
        return scrollPane;
    }
}
