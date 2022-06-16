package com.client.pane.game.space.purchasableSpace.deedState;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.UserType;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.INotPurchasedAction;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.NotPurchasedBotAction;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.NotPurchasedMultiAction;
import com.client.pane.game.space.purchasableSpace.notPurchasedAction.NotPurchasedUserAction;

import java.util.HashMap;

/**
 *   Not purchased state of the property. Player can buy that space
 *   not purchased state action is determined according to the player  which are notPurchasedBotAction and notPurcasedUserAction
 *    a player can buy this space. bot purchase logic is determined in notPurchasedBotAction action
 */
public class NotPurchased implements ISpaceState {


    private HashMap<UserType, INotPurchasedAction> actionType = new HashMap<>();


    /**
     * hash map between bot and  INotPurchasedAction
     */
    public  NotPurchased(){

        actionType.put(UserType.Bot,new NotPurchasedBotAction());
        actionType.put(UserType.User, new NotPurchasedUserAction());
        actionType.put(UserType.MultiPlayer,new NotPurchasedMultiAction());

    }

    @Override
    public ISpaceState Action(IPlayer player , SpaceDeed space) {

     return    actionType.get(player.getUserType()).notPurchasedAction(player,space,this); // determine action of the player according to the player type



    }
}
