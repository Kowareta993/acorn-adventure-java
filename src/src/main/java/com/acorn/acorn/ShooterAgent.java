package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class ShooterAgent extends SimpleAgent implements Shootable {
    private int rate; //shoot rate

    protected ShooterAgent(Location location, Size size) {
        super(location, size);
    }

    @Override
    public void onCollision(MyObject object) {

    }


    private void fire() {
        //TODO
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Resources.enemy2Img, location.getX(), location.getY(), size.getW(), size.getH());
    }
}
