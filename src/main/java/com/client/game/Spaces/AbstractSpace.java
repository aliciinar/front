package com.client.game.Spaces;

public abstract class AbstractSpace implements ISpace {

    protected String name;
    protected int position;

    @Override
    public String getName() {
        return name;
    }
    public int GetPosition(){return position;}

}
