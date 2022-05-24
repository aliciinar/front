package com.client.pane.game.space.PurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;

public class NotPurchasedBotAction implements INotPurchasedAction {
    @Override
    public SpaceState notPurchasedAction(IPlayer player,SpaceDeed deed, SpaceState spaceState) {
        // todo bot satın almadan test yapma kısmı
        //GameManager.getInstance().getSceneType().endTurn();
        //return  spaceState;
        // todo yukarı tarafı kaldırcaz
       if(player.getMoney() > deed.GetCost()){
            // todo AI of the bot
            if(player.getMoney() > 300){
                deed.Purchase(player);
                GameManager.getInstance().getSceneType().endTurn();
                return  new Purchased();
            }
           // GameManager.getInstance().setDeed(deed);
          //  GameManager.getInstance().purchaseActionBot();
          //  GameManager.getInstance().nextTurn();


        }
        GameManager.getInstance().getSceneType().endTurn();
        return  spaceState;
    }
}
