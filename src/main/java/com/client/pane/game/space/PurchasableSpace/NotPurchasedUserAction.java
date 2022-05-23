package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;

public class NotPurchasedUserAction implements  InotPurchasedAction{
    @Override
    public SpaceState notPurchasedAction(IPlayer player,SpaceDeed deed , SpaceState spaceState) {
        GameManager.getInstance().getSceneType().notPurchased(deed); // open  purchased scene
        return  spaceState;

    }
}
