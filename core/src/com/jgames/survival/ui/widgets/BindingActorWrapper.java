package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BindingActorWrapper<T extends Actor> extends Actor implements BindingActor {
    private int boundedX, boundedY;
    private boolean needReplaceAfterRotate;
    private final T wrapped;

    public BindingActorWrapper(T wrapped) {
        this.wrapped = wrapped;
        setX(wrapped.getX());
        setY(wrapped.getY());
        setWidth(wrapped.getWidth());
        setHeight(wrapped.getHeight());
    }

    @Override
    public void setBinding(int x, int y) {
        this.boundedX = x;
        this.boundedY = y;

        this.needReplaceAfterRotate = Float.compare(getX(), x) != 0 && Float.compare(getY(), y) != 0;
    }

    @Override
    protected void rotationChanged() {
        if (!needReplaceAfterRotate) {
            return;
        }

        float x = getX();
        float y = getY();

        float rotation = getRotation();
        float cos = MathUtils.cosDeg(rotation);
        float sin = MathUtils.sinDeg(rotation);

        float xRotated = cos * x - sin * y;
        float yRotated = sin * x + cos * y;
        float dx = boundedX - xRotated;
        float dy = boundedY - yRotated;

        wrapped.setX(x + dx);
        wrapped.setY(y + dy);
        wrapped.setRotation(rotation);

        setX(wrapped.getX());
        setY(wrapped.getY());
        setWidth(wrapped.getWidth());
        setHeight(wrapped.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        wrapped.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        wrapped.draw(batch, parentAlpha);
    }

    @Override
    public float getX() {
        return wrapped.getX();
    }

    @Override
    public float getY() {
        return wrapped.getY();
    }

    public T getWrapped() {
        return wrapped;
    }
}
