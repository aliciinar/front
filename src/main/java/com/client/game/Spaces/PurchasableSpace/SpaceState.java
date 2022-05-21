package com.client.game.Spaces.PurchasableSpace;

import com.client.game.Player.IPlayer;


public interface SpaceState {

    SpaceState Action(IPlayer playingPlayer , SpaceDeed space);

}
