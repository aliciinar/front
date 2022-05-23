package com.client.pane.game.Player;

import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public interface PlayerState {

    Pair<PlayerState,Boolean> Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space);
    PlayerState EndPlay();


    PlayerState determineState(IPlayer player, int diceValue1, int diceValue2 );


}
