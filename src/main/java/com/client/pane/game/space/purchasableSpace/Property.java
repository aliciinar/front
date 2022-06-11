package com.client.pane.game.space.purchasableSpace;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.deedState.NotPurchased;
import com.client.pane.game.space.purchasableSpace.deedState.Purchased;

/**
 * this class is implemented property spaces. State of this class is not purchased when its instantiated.
 */
public class Property extends SpaceDeed {


    public Property(String name , int cost  ){
        this.name = name;
        this.rent = cost / 10;
        this.cost = cost;
        state = new NotPurchased();
    }

    /**
     * player on this space do the action according to the its state
     * @param player active player
     */
    @Override
    public void action(IPlayer player) { //
       // System.out.println("Propery," + name + "player" + player.getName() );

     state = state.Action(player , this);
       // System.out.println("name" + state);
    }

    /**
     * Player purchase this space
     * take cost of the this scpace and set new owner
     * @param player active player
     */

    public void purchase(IPlayer player ){ // player purchase this space

        player.moneyTransition(-cost); // player is giving cost of this space
        setOwner(player); // set owner of this space

        player.setScore(cost); // set player cost
        updateObserver(player); // notify GUI
        state = new Purchased();

    }


}
