package com.client.game.Spaces.PurchasableSpace;

import com.frontend.Player.IPlayer;

public interface SpaceState {

    SpaceState Action(IPlayer playingPlayer , SpaceDeed space);

}
