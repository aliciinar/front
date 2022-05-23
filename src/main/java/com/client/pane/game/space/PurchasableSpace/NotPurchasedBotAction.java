package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;

public class NotPurchasedBotAction implements  InotPurchasedAction{
    @Override
    public void notPurchasedAction(IPlayer player,SpaceDeed deed) {
        if(player.getMoney() > deed.GetCost()){
            // todo AI of the bot
           // GameManager.getInstance().setDeed(deed);
          //  GameManager.getInstance().purchaseActionBot();
            GameManager.getInstance().nextTurn();
        }
    }
}
