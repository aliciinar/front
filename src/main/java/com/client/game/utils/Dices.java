package com.client.game.utils;
import java.util.Random;

/**
 * This Class is used when player throws dices
 */
public class Dices {

    static Random rd = new Random(); // Static Random object to get random integer

    /**
     * This method is used to get 2 random integer for dice values
     * It is a static method so do not need to create dices object
     * @return 2 random integer between 1 and 6 
     */
    public static int[] throwDice(){

        int[] result = new int[2];
        int dice1 , dice2;
        dice1 = rd.nextInt(1,7);
        dice2 = rd.nextInt(1,7);

        result[0] = dice1 + dice2 ;
        result[1] = dice1 - dice2;
        return result;
    }

    public static void debugSetSeedDouble(){
        rd = new Random();
        rd.setSeed(1586536570);
    }


}
