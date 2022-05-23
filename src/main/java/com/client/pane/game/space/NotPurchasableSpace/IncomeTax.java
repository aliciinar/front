package com.client.pane.game.space.NotPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


public class IncomeTax extends AbstractSpace {

    private final int tax = -50;



    @Override
    public void action(IPlayer player) {
      //  System.out.println("Income Tax " + "player " + player.getName());
        //GameManager.getInstance().getSceneType().normal();
        player.moneyTransition(tax);
          GameManager.getInstance().nextTurn();
       // GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Pay Tax" + "\nTax : " + Math.abs(tax);
    }
}
