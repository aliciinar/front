package com.client.game.Spaces.NotPurchasableSpace;

import com.frontend.Player.IPlayer;
import com.frontend.Spaces.AbstractSpace;

public class JailVisit extends AbstractSpace {

    public  JailVisit(String name ,int pos ){
        this.name = name;
        position = pos;
    }
    @Override
    public void action(IPlayer player) {
    }
}
