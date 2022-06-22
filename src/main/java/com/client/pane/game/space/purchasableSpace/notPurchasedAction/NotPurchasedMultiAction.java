package com.client.pane.game.space.purchasableSpace.notPurchasedAction;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

/**
 *  During the multiplayer game if the player come to a not purchased deed determine the action
 */
public class NotPurchasedMultiAction implements INotPurchasedAction{

    /**
     * when multiplayer  user on a deed space, GUI is determined in here
     *
     * @param player activePlayer
     * @param deed current deed
     * @param ISpaceState  state of the space
     * @return new state
     *
     */

    @Override
    public ISpaceState notPurchasedAction(IPlayer player, SpaceDeed deed, ISpaceState ISpaceState) {

        // request information from client
        GameManager.getInstance().getSceneType().notPurchased(deed,GameManager.getInstance().getGameBoard());


        return ISpaceState;
    }
}
