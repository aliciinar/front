package com.client.pane.game.space.purchasableSpace.notPurchasedAction;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

public class NotPurchasedUserAction implements INotPurchasedAction {

    // when user on a deed space, GUI is determined in here



    @Override
    public ISpaceState notPurchasedAction(IPlayer player, SpaceDeed deed , ISpaceState ISpaceState) {
        if(player.getMoney() < deed.getCost()){ // if a user money is less than cost of the deed end the turn
            GameManager.getInstance().endTurn();
        }else{ // if user has enough money open purchase GUI
            GameManager.getInstance().getSceneType().notPurchased(deed,GameManager.getInstance().getGameBoard()); // open  purchased scene

        }
        return ISpaceState;

    }
}
