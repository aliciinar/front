package com.client.dto;

/**
 *  Converts Json to object.
 */
public class ActionDto {
    private String name; // name of the player
    private String type; // type of the action. Movements made according to the this action types
    private int dice1,dice2; // dice values of the roll

    public ActionDto(String name , String type , int dice1 , int dice2){
        this.name = name;
        this.type = type;
        this.dice1 = dice1;
        this.dice2 = dice2;

    }

}
