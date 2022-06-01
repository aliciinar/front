package com.client.pane.game.space.notPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.AbstractSpace;

/**
 * Starting Point Class.
 */
public class StartingPoint extends AbstractSpace {


    /**
     *  Increase money of player 100
     * @param player active Player
     */
    @Override
    public void action(IPlayer player) {
         player.moneyTransition(100);
       GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Start";
    }
}
