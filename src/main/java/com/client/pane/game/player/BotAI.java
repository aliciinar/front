package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerNotPrison;

public class BotAI extends  PlayerAbstract{



    public BotAI( ){

        this.money = 1500;
        this.position = 0;
        this.state = new PlayerNotPrison();
        this.name = "Bot";
        this.userType =  UserType.Bot;
    }

    @Override
    public boolean purchaseSpace(int spacePrice) {
        if(getMoney() > spacePrice){
            return  true;
        }
        return  false;
    }

    @Override
    public UserType getUserType() {
        return  userType;
    }




}
