package com.client.game.Spaces.NotPurchasableSpace;

import com.client.game.Player.IPlayer;
import com.client.game.Spaces.AbstractSpace;


public class JailVisit extends AbstractSpace {

    public  JailVisit(String name ,int pos ){
        this.name = name;
        position = pos;
    }
    @Override
    public void action(IPlayer player) {
    }
}
