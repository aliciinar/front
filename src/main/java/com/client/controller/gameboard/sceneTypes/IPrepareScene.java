package com.client.controller.gameboard.sceneTypes;

import com.client.pane.game.GameBoard;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

/**
 * Controls button events for game.
 */
public interface IPrepareScene {

    // we are determining scene types according to the player type and prepare scene in gameBoard.

    void  notPurchased(SpaceDeed space, GameBoard gameBoardController); //  player on deed space so prepare purchase scene
    void  nextTurnNormal(GameBoard gameBoardController); // prepare scene according to the active player
    void  nextTurnJail(GameBoard gameBoardController); // player in prison so jail time button will open
    void  endTurn(); // prepare end turn button since play is finished


}
