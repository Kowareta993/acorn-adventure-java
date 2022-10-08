package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class SimpleAgent extends Agent implements Shootable {
    protected SimpleAgent(Location location, Size size) {
        super(location, size);
    }

    @Override
    public void onCollision(MyObject object) {
        //TODO
    }


    @Override
    public void onShoot(Bullet bullet) {
        //TODO
    }

    @Override
    public void clear(GraphicsContext graphicsContext) {

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Resources.enemy1Img, location.getX(), location.getY(), size.getW(), size.getH());
    }
}
