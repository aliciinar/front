package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.Player.IPlayer;

public interface InotPurchasedAction {

    SpaceState  notPurchasedAction(IPlayer player,SpaceDeed deed, SpaceState spaceState);
}
