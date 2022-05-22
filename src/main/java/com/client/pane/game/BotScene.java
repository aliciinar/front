package com.client.pane.game;

public class BotScene implements IPrepareScene{
    @Override
    public void prepareScene(GameBoard gameBoard) {
        gameBoard.roll();
    }
}
