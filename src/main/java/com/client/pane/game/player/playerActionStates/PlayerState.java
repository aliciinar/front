package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public interface PlayerState {

    PlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space);


    PlayerState determineState(IPlayer player, int diceValue1, int diceValue2 );


}
