package com.acorn.acorn;
public abstract class Bullet extends MyObject implements Intractable {
    protected Bullet(Location location, Size size) {
        super(location, size);
    }

    @Override
    public abstract void onCollision(MyObject object);
}
