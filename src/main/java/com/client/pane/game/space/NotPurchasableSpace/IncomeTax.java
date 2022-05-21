package com.client.pane.game.space.NotPurchasableSpace;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


public class IncomeTax extends AbstractSpace {

    private final int tax = -50;



    @Override
    public void action(IPlayer player) {
        player.moneyTransition(tax);
    }

    @Override
    public String getName() {
        return "Pay Tax" + "\nTax : " + Math.abs(tax);
    }
}
