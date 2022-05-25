package com.client.pane.game.space.purchasableSpace.deedState;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

/**
 *  deeds have two states which is purchased and not purchased. This state can be changed dynamically so we use state pattern.
 *     this is the interface of the state pattern for deeds.
 */
public interface ISpaceState {



    ISpaceState Action(IPlayer playingPlayer , SpaceDeed space);

}
