package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class Block extends MyObject {

    protected Block(Location location, Size size) {
        super(location, size);
    }


    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(Resources.blockImg, location.getX(), location.getY(), size.getW(), size.getH());
    }
}
