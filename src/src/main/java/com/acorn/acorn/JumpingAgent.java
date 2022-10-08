package com.acorn.acorn;
public abstract class JumpingAgent extends Agent {
    private int leapSize;

    protected JumpingAgent(Location location, Size size) {
        super(location, size);
    }

    @Override
    public void onCollision(MyObject object) {
        //TODO
    }

}
