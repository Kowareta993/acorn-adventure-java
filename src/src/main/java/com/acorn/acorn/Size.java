package com.acorn.acorn;

import java.util.Objects;

public class Size {
    private double w;
    private double h;

    public Size(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return w == size.w && h == size.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(w, h);
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public double getW() {
        return this.w;
    }

    public double getH() {
        return this.h;
    }
}
