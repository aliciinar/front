package com.client.controller.gameboard;

import com.client.pane.game.space.PurchasableSpace.SpaceDeed;

public interface IPrepareScene {

    void  notPurchased(SpaceDeed space,GameBoardController gameBoardController);
    void  nextTurnNormal(GameBoardController gameBoardController);
    void  nextTurnJail(GameBoardController gameBoardController);
    void  endTurn();


}
