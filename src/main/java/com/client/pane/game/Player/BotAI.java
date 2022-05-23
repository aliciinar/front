package com.client.pane.game.Player;

import com.client.pane.game.space.ISpace;

public class BotAI extends  PlayerAbstract{



    public BotAI( ){

        this.money = 1500;
        this.position = 0;
        this.state = new PlayerNotPrison();
    }

    @Override
    public boolean purchaseSpace(int spacePrice) {
        if(getMoney() > spacePrice){
            return  true;
        }
        return  false;
    }

    @Override
    public String getUserType() {
        return  "Bot";
    }




}
