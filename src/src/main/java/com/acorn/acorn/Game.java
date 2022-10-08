package com.acorn.acorn;

import javafx.scene.canvas.GraphicsContext;

public class Game implements Renderable {
    Map map;
    private Timer timer;

//    public void start();   //start playing the game

    public Game() {
        int m = 20;
        int n = 50;
        this.map = new Map(m, n);
    }

    public void playerAction(Action action) {
        this.map.playerAction(action);
    }

    private void onFinish() {   //log the game on finish
        //TODO
    }

    private void handleCollisions() {
        //TODO
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        this.map.render(graphicsContext);
    }

}
