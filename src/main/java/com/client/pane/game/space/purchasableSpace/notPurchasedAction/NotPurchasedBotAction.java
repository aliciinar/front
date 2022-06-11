package com.client.pane.game.space.purchasableSpace.notPurchasedAction;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.deedState.Purchased;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

/**
 * If a bot in deed space which is not purchased, whether the bot is buy or not determined in there.
 *
 */
public class NotPurchasedBotAction implements INotPurchasedAction {


    /**
     * if bot has money more than deed cost, and if the bot has more than 300 money, bot will buy the space
     * @param player active plauer
     * @param deed  current deed
     * @param ISpaceState new state
     * @return
     */


    @Override
    public ISpaceState notPurchasedAction(IPlayer player, SpaceDeed deed, ISpaceState ISpaceState) {

       if(player.getMoney() > deed.getCost()){
            // todo AI of the bot
            if(player.getMoney() > 300){
                deed.purchase(player);

                GameManager.getInstance().getSceneType().endTurn();
                return  new Purchased();
            }



        }
        GameManager.getInstance().getSceneType().endTurn(); // bot does not have enough money end turn
        return ISpaceState;
    }
}
