package com.client.game.Spaces.NotPurchasableSpace;
import com.frontend.Player.IPlayer;
import com.frontend.Spaces.AbstractSpace;

public class StartingPoint extends AbstractSpace {


    public  StartingPoint(String name ,int pos ){
        position = pos;
        this.name = name;
    }
    @Override
    public void action(IPlayer player) {

    }
}
