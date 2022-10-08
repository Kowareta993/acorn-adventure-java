package com.acorn.acorn;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Map implements Renderable {
    private ArrayList<MyObject> objects;
    private ArrayList<Block> blocks;
    private Flag flag;
    private Player player;
    private ArrayList<Agent> agents;
    private ArrayList<Mushroom> mushrooms;
    private boolean[][] used;
    private Location bound;
    public static int blockSize = 25;
    private final int rows;
    private final int cols;
    private Canvas canvas;

    public Map(int m, int n) {
        this.rows = m;
        this.cols = n;
        createMap();
    }

    private void createMap() {
        this.blocks = new ArrayList<>();
        this.used = new boolean[rows][cols];
        this.agents = new ArrayList<>();
        this.mushrooms = new ArrayList<>();
        this.objects = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                used[i][j] = false;
            }
        }
        addGround();
        addFlag();
        addBlocks();
        addPlayer();
        addEnemies();
        render();
    }

    private void addFlag() {
        this.flag = new Flag(new Location((cols - 2) * blockSize, (rows - 2) * blockSize), new Size(blockSize, blockSize));
        used[rows - 2][cols - 2] = true;
        this.objects.add(flag);
    }

    private void addEnemies() {
        for (int i = 6; i < cols; i++) {
            if (!used[rows - 2][i]) {
                if (Math.random() > 0.1) continue;
                used[rows - 2][i] = true;
                if (Math.random() > 0.5) {
                    Agent enemy = new SimpleAgent(new Location(i * blockSize, (rows - 2) * blockSize), new Size(blockSize, blockSize));
                    this.agents.add(enemy);
                    this.objects.add(enemy);
                } else if (Math.random() > 0.5) {
                    Agent enemy = new ShooterAgent(new Location(i * blockSize, (rows - 2) * blockSize), new Size(blockSize, blockSize));
                    this.agents.add(enemy);
                    this.objects.add(enemy);
                } else {
                    Agent enemy = new ProtectedAgent(new Location(i * blockSize, (rows - 2) * blockSize), new Size(blockSize, blockSize));
                    this.agents.add(enemy);
                    this.objects.add(enemy);
                }
                i += 3;
            }
        }
    }

    private void addPlayer() {
        this.player = new Player(new Location(3 * blockSize, (rows - 3) * blockSize), new Size(blockSize, 2 * blockSize), this);
        this.objects.add(player);
        used[rows - 3][3] = true;
        used[rows - 2][3] = true;
    }

    private void addGround() {
        for (int i = 0; i < cols; i++) {
            Block block = new Block(new Location(i * blockSize, (rows - 1) * blockSize), new Size(blockSize, blockSize));
            used[rows - 1][i] = true;
            this.blocks.add(block);
            this.objects.add(block);
        }
    }

    private void addBlocks() {
        double p = 0.2;
        for (int i = 5; i < cols - 1; i++) {
            if (Math.random() > p || used[rows - 2][i]) continue;
            int height = (int) (Math.random() * 2) + 1;
            for (int j = 0; j < height; j++) {
                Block block = new Block(new Location(i * blockSize, (rows - 2 - j) * blockSize), new Size(blockSize, blockSize));
                used[rows - 2 - j][i] = true;
                this.blocks.add(block);
                this.objects.add(block);
            }
            if (Math.random() > 0.5) {
                Mushroom mushroom = new Mushroom(new Location(i * blockSize, (rows - 2 - height) * blockSize), new Size(blockSize, blockSize));
                this.mushrooms.add(mushroom);
                this.objects.add(mushroom);
                used[rows - 2 - height][i] = true;
            }
        }
    }

    public boolean isBlocked(Rectangle2D rect) {
        for (Block block : this.blocks) {
            if (block.getBoundary().intersects(rect))
                return true;
        }
        return false;
    }

    public void rerender(Movable object) {
//        Location preLoc = this.mapp.get(object);
//        this.canvas.getGraphicsContext2D().clearRect(preLoc.getY() * blockSize, blockSize * (preLoc.getX() - object.getSize().getH() + 1), object.getSize().getW() * blockSize, object.getSize().getH() * blockSize);
        object.clear(canvas.getGraphicsContext2D());
        object.render(canvas.getGraphicsContext2D());
    }

    public Player getPlayer() {
        return this.player;
    }

    public void playerAction(Action action) {
        this.player.doAction(action);
//        rerender(player);
    }

    //    public abstract Object getObject(Location location);
//
//

    public boolean isValid(Rectangle2D rect) {
        if (rect.getMinX() < 0 || rect.getMinX() > cols * blockSize)
            return false;
        if (rect.getMaxX() < 0 || rect.getMaxX() > cols * blockSize)
            return false;
        if (rect.getMinY() < 0 || rect.getMinY() > rows * blockSize)
            return false;
        if (rect.getMaxY() < 0 || rect.getMaxY() > rows * blockSize)
            return false;
        return !isBlocked(rect);
    }

    private void render() {
        this.canvas = new Canvas(cols * blockSize, rows * blockSize);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        for (MyObject object : this.objects)
            object.render(graphicsContext);

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        rerender(player);
        Canvas canvas = new Canvas(cols * blockSize, rows * blockSize);
        canvas.getGraphicsContext2D().drawImage(Resources.bgImg, 0, 0, cols * blockSize, rows * blockSize);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage image = canvas.snapshot(params, null);
        WritableImage image2 = this.canvas.snapshot(params, null);
        graphicsContext.clearRect(0, 0, cols * blockSize, rows * blockSize);
        double x = Math.min((player.getLocation().getX() - 3 * blockSize), cols * blockSize - App.width);
        if (x < 0)
            x = 0;
        graphicsContext.drawImage(image, -x, 0);
        graphicsContext.drawImage(image2, -x, 0);
        for (Agent agent : this.agents) {
            if (agent.intersects(player)) {
                System.out.println("You Lost!");
                Platform.exit();
            }
        }
        if (flag.intersects(player)) {
            System.out.println("You Won!");
            Platform.exit();
        }
    }
}
