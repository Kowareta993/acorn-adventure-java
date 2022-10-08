package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class Mushroom extends MyObject implements Intractable {
    private Buff buff;

    protected Mushroom(Location location, Size size) {
        super(location, size);
    }

    @Override
    public void onCollision(MyObject object) {

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Resources.mushroomImg, location.getX(), location.getY(), size.getW(), size.getH());
    }
}
