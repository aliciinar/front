package com.client.pane.game.space.notPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.AbstractSpace;

/**
 * Income Tax class
 */
public class IncomeTax extends AbstractSpace {

    private final int tax = -50;



    @Override
    public void action(IPlayer player) {
        player.moneyTransition(tax);
        GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Pay Tax" + "\nTax : " + Math.abs(tax);
    }
}
