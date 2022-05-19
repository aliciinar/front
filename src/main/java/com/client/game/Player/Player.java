package com.client.game.Player;

import com.client.game.Spaces.ISpace;

import java.util.*;

public class Player extends  PlayerAbstract{


    public Player(int playerID , List<ISpace> spaces){

        this.playerID = playerID;
        this.position = 0;
        this.money = 1500;
        this.state = new PlayerNotPrison();
        this.ISpaces = spaces;
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
