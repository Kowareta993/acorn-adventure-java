package com.acorn.acorn;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static int width = 500;
    public static int height = 500;
    public static int fps = 60;
    private Game game;
    private Canvas canvas;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = this.createScene();
        scene.addEventHandler(KeyEvent.ANY, this::handleKeyEvent);
        stage.setScene(scene);
        stage.setTitle("Acorn");
        stage.setResizable(false);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                game.render(canvas.getGraphicsContext2D());
            }
        }.start();
        stage.show();
    }

    private Scene createScene() {
        Group root = new Group();
        this.game = new Game();
        this.canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        return new Scene(root, width, height);
    }

    private void handleKeyEvent(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED)
            switch (event.getCode()) {
                case D, RIGHT -> {
                    this.game.playerAction(Action.RIGHT);
                }
                case A, LEFT -> {
                    this.game.playerAction(Action.LEFT);
                }
                case W, UP -> {
                    this.game.playerAction(Action.JUMP);
                }
            }
        if (event.getEventType() == KeyEvent.KEY_RELEASED)
            switch (event.getCode()) {
                case D, RIGHT -> {
                    this.game.playerAction(Action.RIGHT_R);
                }
                case A, LEFT -> {
                    this.game.playerAction(Action.LEFT_R);
                }
            }
    }

    public static void main(String[] args) {
        launch();
    }
}