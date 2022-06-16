package com.client.pane.game.space.purchasableSpace.notPurchasedAction;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

public class NotPurchasedMultiAction implements INotPurchasedAction{



    @Override
    public ISpaceState notPurchasedAction(IPlayer player, SpaceDeed deed, ISpaceState ISpaceState) {

        // request information from client
        GameManager.getInstance().getSceneType().notPurchased(deed,GameManager.getInstance().getGameBoard());


        return ISpaceState;
    }
}
