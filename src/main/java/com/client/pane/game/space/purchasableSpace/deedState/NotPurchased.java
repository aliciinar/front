package com.client.pane.game.space.purchasableSpace.deedState;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.UserType;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.INotPurchasedAction;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.NotPurchasedBotAction;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.NotPurchasedUserAction;

import java.util.HashMap;


public class NotPurchased implements ISpaceState {

    // Not purchased state of the property. Player can buy that space
    // not purchased state action is determined according to the player  which are notPurchasedBotAction and notPurcasedUserAction
    // a player can buy this space. // bot purchase logic is determined in notPurchasedBotAction action
    private HashMap<UserType, INotPurchasedAction> actionType = new HashMap<>();

    public  NotPurchased(){

        actionType.put(UserType.Bot,new NotPurchasedBotAction()); // hash map between bot and  INotPurchasedAction
        actionType.put(UserType.User, new NotPurchasedUserAction()); // hash map between user and INotPurchasedAction

    }

    @Override
    public ISpaceState Action(IPlayer player , SpaceDeed space) {

     return    actionType.get(player.getUserType()).notPurchasedAction(player,space,this); // determine action of the player according to the player type



    }
}
