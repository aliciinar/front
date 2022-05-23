package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;

public class NotPurchasedBotAction implements  InotPurchasedAction{
    @Override
    public SpaceState notPurchasedAction(IPlayer player,SpaceDeed deed, SpaceState spaceState) {
        if(player.getMoney() > deed.GetCost()){
            // todo AI of the bot
           // GameManager.getInstance().setDeed(deed);
          //  GameManager.getInstance().purchaseActionBot();
          //  GameManager.getInstance().nextTurn();
            GameManager.getInstance().getSceneType().endTurn();
            return  spaceState;
        }
        return  spaceState;
    }
}
