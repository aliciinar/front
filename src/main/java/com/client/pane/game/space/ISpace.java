package com.client.pane.game.space;
import com.client.pane.game.player.IPlayer;

/**
 * Interface for spaces on game board
 * Spaces responsible for actions of the player in a space.
 *
 */
public interface ISpace {
    /**
     * when a player comes  a space do some action
     * @param player
     */
    void   action(IPlayer player);

    /**
     *
     * @return  name of the space
     */
    String getName();
}
