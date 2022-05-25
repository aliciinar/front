package com.client.pane.game.space.purchasableSpace.deedState;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

/**
 * this is the state of deed spaces when they have an owner
 */
public class Purchased implements ISpaceState {

    /**
     * if player is owner of the this space end this turn
     *  player give rent of this space
     *  owner take rent of this space
     * @param player active player
     * @param space current space
     * @return
     */

    @Override
    public ISpaceState Action(IPlayer player , SpaceDeed space) {


        if(player.equals(space.getOwner())) {
            GameManager.getInstance().getSceneType().endTurn();
            return  this;
        }

        int rent = space.getRent();

        player.moneyTransition(-rent);
        space.getOwner().moneyTransition(rent);
        GameManager.getInstance().getSceneType().endTurn();
        return this;
    }

}
