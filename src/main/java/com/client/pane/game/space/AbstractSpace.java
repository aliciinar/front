package com.client.pane.game.space;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.SpaceManager;
import com.client.pane.game.player.IPlayer;

/**
 * abstract class of the space. general methods of the spaces implemented here.
 */
public abstract class AbstractSpace implements ISpace {

    protected String name;
    protected int position;

    @Override
    public String getName() {
        return name;
    }
    public int getPosition(){return position;}


    /**
     * update GUI according the action of the spaces
     * @param player
     */
    protected void updateObserver(IPlayer player){
        IObserverText observerText =  SpaceManager.getInstance().getBoardSpace(this);
        observerText.updateOwner(player);


    }

}
