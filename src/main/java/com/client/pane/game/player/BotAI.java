package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerNotPrisonState;


public class BotAI extends  PlayerAbstract{
    /**
     * Bot ai
     */


    public BotAI( ){

        this.money = 1500;
        this.position = 0;
        this.state = new PlayerNotPrisonState();
        this.name = "Bot";
        this.userType =  UserType.Bot;
    }



    @Override
    public UserType getUserType() {
        return  userType;
    }




}
