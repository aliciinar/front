package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.UserType;

import java.util.HashMap;


public class NotPurchased implements SpaceState{

    HashMap<UserType, INotPurchasedAction> actionType = new HashMap<>();

    public  NotPurchased(){

        actionType.put(UserType.Bot,new NotPurchasedBotAction());
        actionType.put(UserType.User, new NotPurchasedUserAction());

    }

    @Override
    public SpaceState Action(IPlayer player , SpaceDeed space) {
      //  System.out.println("Not purchased"  + "player" + player.getName() );

     return    actionType.get(player.getUserType()).notPurchasedAction(player,space,this);



    }
}
