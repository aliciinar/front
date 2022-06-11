package com.client.dto;

public class ActionDto {
    private String name;
    private String type;
    private int dice1,dice2;

    public ActionDto(String name , String type , int dice1 , int dice2){
        this.name = name;
        this.type = type;
        this.dice1 = dice1;
        this.dice2 = dice2;

    }

}
