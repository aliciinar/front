package com.client.pane.game;

import com.client.pane.game.space.PurchasableSpace.SpaceDeed;

public interface IPrepareScene {

    void  notPurchased(SpaceDeed space);
    void  normal();
    void  nextTurnNormal(GameBoard gameBoard);
    void  nextTurnJail(GameBoard gameBoard);

    void playerEnd(GameBoard gameBoard);

    void  endTurn();

}
