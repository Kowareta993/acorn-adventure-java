package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class ProtectedAgent extends ShooterAgent {
    protected ProtectedAgent(Location location, Size size) {
        super(location, size);
    }

    @Override
    public void onCollision(MyObject object) {

    }


    @Override
    public void onShoot(Bullet bullet) {

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Resources.enemy3Img, location.getX(), location.getY(), size.getW(), size.getH());
    }
}
