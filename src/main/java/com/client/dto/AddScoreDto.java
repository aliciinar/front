package com.client.dto;


/**
 * Converts Json to object.
 */
public class AddScoreDto {

    private String name;
    private int score;

    public AddScoreDto(String name , int score){
        this.name = name;
        this.score = score;

    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

}
