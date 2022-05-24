package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerNotPrison;

public class Player extends  PlayerAbstract{


    public Player( String name ){

        this.money = 1500;
        this.name = name;
        this.position = 0;
        this.state = new PlayerNotPrison();
        this.userType =  UserType.User;
    }


    @Override
    public boolean purchaseSpace(int spacePrice) {
        if(money > spacePrice){
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public UserType getUserType() {
        return  userType;
    }


}
