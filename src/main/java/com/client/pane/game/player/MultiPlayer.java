package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerNotPrisonState;

public class MultiPlayer extends  PlayerAbstract{

    public MultiPlayer( String name ){

        this.money = 1500;
        this.name = name;
        this.position = 0;
        this.state = new PlayerNotPrisonState();
        this.userType =  UserType.MultiPlayer;
    }




    @Override
    public UserType getUserType() {
        return  userType;
    }
}
