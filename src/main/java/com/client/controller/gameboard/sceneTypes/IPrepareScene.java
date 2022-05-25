package com.client.controller.gameboard.sceneTypes;

import com.client.controller.gameboard.GameBoardController;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

public interface IPrepareScene {

    // we are determining scene types according to the player type and prepare scene in gameBoard.

    void  notPurchased(SpaceDeed space, GameBoardController gameBoardController); //  player on deed space so prepare purchase scene
    void  nextTurnNormal(GameBoardController gameBoardController); // prepare scene according to the active player
    void  nextTurnJail(GameBoardController gameBoardController); // player in prison so jail time button will open
    void  endTurn(); // prepare end turn button since play is finished


}
