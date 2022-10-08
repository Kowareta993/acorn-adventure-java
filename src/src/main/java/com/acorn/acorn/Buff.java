package com.acorn.acorn;
public abstract class Buff {    //buffs (prizes) which given to player by eating mushrooms
    protected int duration; //duration of buff

    protected abstract void decreaseDuration(int ticks);  //decrease buff duration

    protected abstract boolean finished();  //check if buff finished
}
