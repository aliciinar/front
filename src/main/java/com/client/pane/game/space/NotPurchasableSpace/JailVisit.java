package com.client.pane.game.space.NotPurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


public class JailVisit extends AbstractSpace {


    @Override
    public void action(IPlayer player) {
       // System.out.println("Visitjail " + "player " + player.getName());
       // GameManager.getInstance().nextTurn();
        GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Jail Visit";
    }
}
