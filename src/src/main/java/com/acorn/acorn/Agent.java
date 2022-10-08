package com.acorn.acorn;

public abstract class Agent extends MyObject implements Movable, Intractable {
    protected int strength;
    protected int speed;

    protected Agent(Location location, Size size) {
        super(location, size);
    }
}
