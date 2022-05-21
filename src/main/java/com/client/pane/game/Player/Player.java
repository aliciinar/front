package com.client.pane.game.Player;

import com.client.pane.game.space.ISpace;

import java.util.*;

public class Player extends  PlayerAbstract{


    public Player( String name ){

        this.money = 1500;
        this.name = name;
        this.position = 0;
        this.state = new PlayerNotPrison();
    }


    @Override
    public boolean purchaseSpace(int spacePrice) {
        return false;
    }
}
