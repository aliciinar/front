package com.client.pane.game.Player;


public class PlayerNotPrison implements PlayerState{

    private int diceThrow;

    PlayerNotPrison(){
        diceThrow = 2;
    }

    @Override
    public PlayerState Play(IPlayer player) {

        if(diceThrow == 0) return new PlayerPrison();
        return this;

    }

    @Override
    public PlayerState EndPlay(){
        System.out.println(diceThrow);
        if (diceThrow > 0) {
            diceThrow = 2 ;
            return this ;
        }
        else return new PlayerPrison() ;
    }




}
