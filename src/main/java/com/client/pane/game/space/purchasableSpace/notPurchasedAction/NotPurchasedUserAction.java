package com.client.pane.game.space.purchasableSpace.notPurchasedAction;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

public class NotPurchasedUserAction implements INotPurchasedAction {


    /**
     * when user on a deed space, GUI is determined in here
     * if user has enough money open purchase GUI
     * @param player activePlayer
     * @param deed current deed
     * @param ISpaceState  state of the space
     * @return new state
     *
     */


    @Override
    public ISpaceState notPurchasedAction(IPlayer player, SpaceDeed deed , ISpaceState ISpaceState) {
        if(player.getMoney() < deed.getCost()){
            GameManager.getInstance().endTurn();
        }else{
            GameManager.getInstance().getSceneType().notPurchased(deed,GameManager.getInstance().getGameBoard());

        }
        return ISpaceState;

    }
}
