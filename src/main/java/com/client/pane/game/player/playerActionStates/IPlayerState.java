package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

public interface IPlayerState {
    // interface for player states
    IPlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space);  // do action according to the state


    IPlayerState determineState(IPlayer player, int diceValue1, int diceValue2 ); // determine state according to the dice value


}
