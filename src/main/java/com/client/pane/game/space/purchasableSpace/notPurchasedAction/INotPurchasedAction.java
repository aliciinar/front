package com.client.pane.game.space.purchasableSpace.notPurchasedAction;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

public interface INotPurchasedAction {
    // this is the interface of INotPurchasedAction. Player purchase action is determined in this interface according to the player type
    // Deed spaces can invoke this interface if they are in notPurchased state
    ISpaceState notPurchasedAction(IPlayer player, SpaceDeed deed, ISpaceState ISpaceState);
}
