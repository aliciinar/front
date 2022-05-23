package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;

public class NotPurchasedUserAction implements  InotPurchasedAction{
    @Override
    public void notPurchasedAction(IPlayer player,SpaceDeed deed) {
        GameManager.getInstance().getSceneType().notPurchased(deed); // open  purchased scene

    }
}
