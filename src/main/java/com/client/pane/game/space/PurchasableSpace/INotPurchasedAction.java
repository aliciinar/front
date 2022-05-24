package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.player.IPlayer;

public interface INotPurchasedAction {

    SpaceState  notPurchasedAction(IPlayer player,SpaceDeed deed, SpaceState spaceState);
}
