package com.client.pane.game.space;
import com.client.pane.game.Player.IPlayer;


public interface ISpace {

    void  action(IPlayer player); // when a player comes  a space do some action
    String getName();
}
