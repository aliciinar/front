package com.client.pane.game.space;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.SpaceManager;
import com.client.pane.game.player.IPlayer;

/**
 * abstract class of the space. general methods of the spaces implemented here.
 */
public abstract class AbstractSpace implements ISpace {

    protected String name; // name of the space
    protected int position; // position of the space on game board. It return the index of the space in space manager list

    @Override
    public String getName() {
        return name;
    }
    public int getPosition(){return position;}

    protected void updateObserver(IPlayer player){ // update GUI according the action of the spaces
        IObserverText observerText =  SpaceManager.getInstance().getBoardSpace(this);
        observerText.updateOwner(player);


    }

}
