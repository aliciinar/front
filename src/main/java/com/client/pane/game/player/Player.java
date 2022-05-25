package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerNotPrisonState;

public class Player extends  PlayerAbstract{
    //a normal user is a player

    public Player( String name ){

        this.money = 1500;
        this.name = name;
        this.position = 0;
        this.state = new PlayerNotPrisonState();
        this.userType =  UserType.User;
    }




    @Override
    public UserType getUserType() {
        return  userType;
    }


}
