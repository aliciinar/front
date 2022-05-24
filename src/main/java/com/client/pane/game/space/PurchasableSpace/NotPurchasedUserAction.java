package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;

public class NotPurchasedUserAction implements INotPurchasedAction {
    @Override
    public SpaceState notPurchasedAction(IPlayer player,SpaceDeed deed , SpaceState spaceState) {
        GameManager.getInstance().getSceneType().notPurchased(deed,GameManager.getInstance().getGameBoard()); // open  purchased scene
        return  spaceState;

    }
}
