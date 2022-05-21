package com.client.pane.game.Player;

import com.client.pane.game.space.ISpace;

import java.util.*;

public class Player extends  PlayerAbstract{


    public Player(int playerID , String name){

        this.playerID = playerID;
        this.position = 0;
        this.money = 1500;
        this.name = name;
        this.state = new PlayerNotPrison();
    }


    @Override
    public boolean purchaseSpace(int spacePrice) {
        System.out.print("If u want to but write y");

        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();
        if(str.equals('y')){
            return  true;
        }
        return  false;
    }
}
