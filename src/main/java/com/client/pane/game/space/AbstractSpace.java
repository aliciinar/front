package com.client.pane.game.space;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.SpaceManager;
import com.client.pane.game.player.IPlayer;

public abstract class AbstractSpace implements ISpace {

    protected String name;
    protected int position;

    @Override
    public String getName() {
        return name;
    }
    public int GetPosition(){return position;}

    protected void updateObserver(IPlayer player){
        IObserverText observerText =  SpaceManager.getInstance().getBoardSpace(this);
        observerText.updateOwner(player);


    }

}
