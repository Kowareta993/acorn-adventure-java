package com.acorn.acorn;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

public class Resources {
    public static Image blockImg = new Image(Resources.class.getClassLoader().getResourceAsStream("block.png"));
    public static Image acornImg = new Image(Resources.class.getClassLoader().getResourceAsStream("acorn.png"));
    public static Image bgImg = new Image(Resources.class.getClassLoader().getResourceAsStream("background.png"));
    public static Image enemy1Img = new Image(Resources.class.getClassLoader().getResourceAsStream("enemy1.png"));
    public static Image enemy2Img = new Image(Resources.class.getClassLoader().getResourceAsStream("enemy2.png"));
    public static Image enemy3Img = new Image(Resources.class.getClassLoader().getResourceAsStream("enemy3.png"));
    public static Image mushroomImg = new Image(Resources.class.getClassLoader().getResourceAsStream("mushroom.png"));
    public static Image flagImg = new Image(Resources.class.getClassLoader().getResourceAsStream("flag.png"));
}
