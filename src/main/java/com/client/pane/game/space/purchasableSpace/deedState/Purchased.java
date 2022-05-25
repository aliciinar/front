package com.client.pane.game.space.purchasableSpace.deedState;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;


public class Purchased implements ISpaceState {

    // this is the state of deed spaces when they have an owner

    @Override
    public ISpaceState Action(IPlayer player , SpaceDeed space) {


        if(player.equals(space.getOwner())) { // if player is owner of the this space end this turn
            GameManager.getInstance().getSceneType().endTurn();
            return  this;
        }

        int rent = space.getRent();  // rent of the space

        player.moneyTransition(-rent); // player give rent of this space
        space.getOwner().moneyTransition(rent); // owner take rent of this space
        GameManager.getInstance().getSceneType().endTurn(); // end the turn
        return this;
    }

}
