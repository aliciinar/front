package com.client.pane.game;

public class PlayerScene implements  IPrepareScene{
    @Override
    public void prepareScene(GameBoard gameBoard) {
        gameBoard.activationRollButton(false);
    }
}
