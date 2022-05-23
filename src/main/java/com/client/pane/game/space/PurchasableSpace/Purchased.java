package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;


public class Purchased implements SpaceState{

    @Override
    public SpaceState Action(IPlayer player , SpaceDeed space) {
        //System.out.println("Purchased,"  + player.getName());
      //  GameManager.getInstance().getSceneType().normal();
        if(player.equals(space.GetOwner())) return this;

        int rent = space.GetRent();

        player.moneyTransition(-rent);
        space.GetOwner().moneyTransition(rent);
        GameManager.getInstance().getSceneType().endTurn();
        return this;
    }

}
