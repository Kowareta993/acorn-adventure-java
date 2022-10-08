package com.acorn.acorn;
public abstract class Timer {
    private int ticks;

    public abstract int getTime();  //get time

    public abstract void increaseTime(int ticks);   //increase time by ticks, can be used as thread worker later on
}
