package com.acorn.acorn;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Player extends Agent implements Shootable {
    static double jumpSpeed = Map.blockSize * 8;
    private int leapSize;
    private int lives;
    private int score;
    private boolean jumping, falling;
    private double vx, vy;
    private Map map;
    private double totalJump;

    private List<Buff> buffs;

    protected Player(Location location, Size size, Map map) {
        super(location, size);
        this.leapSize = 3 * Map.blockSize;
        this.jumping = false;
        this.falling = false;
        this.speed = 5 * Map.blockSize;
        this.map = map;
        vx = 0;
        vy = 0;
    }

    @Override
    public void onCollision(MyObject object) {
        //TODO
    }

    public void doAction(Action action) {
        switch (action) {
            case RIGHT -> {
                vx = this.speed;
            }
            case LEFT -> {
                vx = -this.speed;
            }
            case JUMP -> {
                if (!jumping && !falling) {
                    jumping = true;
                    vy = -jumpSpeed;
                    totalJump = 0;
                }
            }
            case RIGHT_R, LEFT_R -> {
                vx = 0;
            }
        }
    }

    @Override
    public void onShoot(Bullet bullet) {
        //TODO
    }

    private Location jump(Map map) {
        //TODO
        return null;
    }

    private void eatMushroom(Mushroom mushroom) {
        //TODO
    }

    private Action getPlayerAction() {
        //TODO
        return null;
    }

    private void fire() {
        //TODO
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        double x, y;
        if (jumping && totalJump >= this.leapSize) {
            jumping = false;
            vy = jumpSpeed;
            falling = true;
        }
        if (!jumping && map.isValid(new Rectangle2D(location.getX(), location.getY() + 5, getSize().getW(), getSize().getH()))) {
            vy = jumpSpeed;
            falling = true;
        }
        x = location.getX() + vx / App.fps;
        y = location.getY() + vy / App.fps;
        if (!map.isValid(new Rectangle2D(x, y, size.getW(), size.getH()))) {
            jumping = false;
            falling = true;
            if (vy < 0) vy = jumpSpeed;
            else vy = 0;
            vx = 0;
            graphicsContext.drawImage(Resources.acornImg, location.getX(), location.getY(), size.getW(), size.getH());
            return;
        }
        totalJump += Math.abs(vy / App.fps);
        this.location = new Location(x, y);
        graphicsContext.drawImage(Resources.acornImg, x, y, size.getW(), size.getH());
    }


    @Override
    public void clear(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(location.getX(), location.getY(), size.getW(), size.getH());
    }
}
