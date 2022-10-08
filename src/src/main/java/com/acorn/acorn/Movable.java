package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public interface Movable extends Renderable {  //interface for implementing the ability of moving in game
    void clear(GraphicsContext graphicsContext);
}
