package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class Flag extends MyObject implements Intractable {
    protected Flag(Location location, Size size) {
        super(location, size);
    }

    @Override
    public void onCollision(MyObject object) {

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Resources.flagImg, location.getX(), location.getY(), size.getW(), size.getH());

    }
}
