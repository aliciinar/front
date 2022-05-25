package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerNotPrisonState;

/**
 * Bot ai
 */
public class BotAI extends  PlayerAbstract{



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
