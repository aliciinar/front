package com.client.pane.game.space.NotPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


public class StartingPoint extends AbstractSpace {

    @Override
    public void action(IPlayer player) {
        //System.out.println("Staring Point " + "player " + player.getName());
       // GameManager.getInstance().getSceneType().normal();
        player.moneyTransition(100);
        GameManager.getInstance().nextTurn();
       // GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Start";
    }
}
