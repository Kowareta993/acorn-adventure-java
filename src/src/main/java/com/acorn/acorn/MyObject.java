package com.acorn.acorn;

import javafx.geometry.Rectangle2D;

import java.util.Objects;

public abstract class MyObject implements Renderable {
    static int class_id = 0;

    protected MyObject(Location location, Size size) {
        this.location = location;
        this.size = size;
        this.id = class_id++;
    }

    protected Location location;
    protected Size size;
    protected int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return id == myObject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Location getLocation() {
        return location;
    }

    public Size getSize() {
        return size;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(location.getX(), location.getY(), size.getW(), size.getH());
    }

    public boolean intersects(MyObject object) {
        return this.getBoundary().intersects(object.getBoundary());
    }
}
