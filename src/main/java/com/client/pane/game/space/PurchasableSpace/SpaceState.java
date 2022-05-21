package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.Player.IPlayer;


public interface SpaceState {

    SpaceState Action(IPlayer playingPlayer , SpaceDeed space);

}
