package com.client.pane.game.Player;

public class PlayerPrison implements PlayerState{

    private int prisonTime ;

    PlayerPrison(){
        prisonTime = 2;
    }
    @Override
    public PlayerState Play(IPlayer player ) {
        prisonTime = prisonTime - 1 ;
        return this;
    }

    @Override
    public PlayerState EndPlay() {
        if (prisonTime == 0) return new PlayerNotPrison();
        else return this;
    }
}
