package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

/**
 * interface for player states
 */
public interface IPlayerState {

    IPlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space);  // do action according to the state


    IPlayerState determineState(IPlayer player, int diceValue1, int diceValue2 ); // determine state according to the dice value


}
