package com.client.pane.game.space.notPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


public class StartingPoint extends AbstractSpace {

    @Override
    public void action(IPlayer player) {
         player.moneyTransition(100);
       GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Start";
    }
}
