package com.client.pane.game.space.NotPurchasableSpace;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


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
