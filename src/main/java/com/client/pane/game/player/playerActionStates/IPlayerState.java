package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

/**
 * interface for normal player states
 * Player is responsible for results of dice values.According to the dice values player can go to the jail or play again
 *
 */
public interface IPlayerState {

    IPlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space);  // do action according to the state

    /**
     * state might be changed according to the dice values. For instance if the player roll double three time player new state will be playerWillGoPrisionState
     * @param player active player
     * @param diceValue1 dice value1
     * @param diceValue2 dice value2
     * @return new player state
     */
    IPlayerState determineState(IPlayer player, int diceValue1, int diceValue2 ); // determine state according to the dice value


}
