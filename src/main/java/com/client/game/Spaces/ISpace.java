package com.client.game.Spaces;
import com.client.game.Player.IPlayer;


public interface ISpace {

    void  action(IPlayer player); // when a player comes  a space do some action
    int GetPosition();
    String getName();
}
