package com.client.pane.game.space.NotPurchasableSpace;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.AbstractSpace;


public class JailVisit extends AbstractSpace {


    @Override
    public void action(IPlayer player) {

        GameManager.getInstance().getSceneType().endTurn();
    }

    @Override
    public String getName() {
        return "Jail Visit";
    }
}
