package com.client.game.Spaces.NotPurchasableSpace;
import com.frontend.Player.IPlayer;
import com.frontend.Spaces.AbstractSpace;

public class IncomeTax extends AbstractSpace {

    private final int tax = -50;

    public  IncomeTax(String name ,int pos ){
        this.name = name;
        position = pos;
    }

    @Override
    public void action(IPlayer player) {
        player.moneyTransition(tax);
    }
}
