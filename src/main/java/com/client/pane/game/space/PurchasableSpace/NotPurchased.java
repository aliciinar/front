package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.GameBoard;
import com.client.pane.game.Player.IPlayer;

import java.util.HashMap;


public class NotPurchased implements SpaceState{

    HashMap<String, InotPurchasedAction> actionType = new HashMap<>();

    public  NotPurchased(){

        actionType.put("Bot",new NotPurchasedBotAction());
        actionType.put("User", new NotPurchasedUserAction());

    }

    @Override
    public SpaceState Action(IPlayer player , SpaceDeed space) {
      //  System.out.println("Not purchased"  + "player" + player.getName() );

        actionType.get(player.getUserType()).notPurchasedAction(player,space);

       // space.Purchase(player , - space.GetCost());
        return null;

      /*
        boolean purchase = player.purchaseSpace(space.cost);

        //Get Action From Player via GUI

        if(purchase) {
            space.Purchase(player , - space.GetCost());
            return new Purchased();
        }
        else return this;*/

    }
}
