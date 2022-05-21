package com.client.game.Spaces.NotPurchasableSpace;
import com.client.game.Player.IPlayer;
import com.client.game.Spaces.AbstractSpace;


public class StartingPoint extends AbstractSpace {


    public  StartingPoint(String name ,int pos ){
        position = pos;
        this.name = name;
    }
    @Override
    public void action(IPlayer player) {

    }
}
