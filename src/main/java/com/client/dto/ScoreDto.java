package com.client.dto;

/**
 * Converts Json to object.
 */
public class ScoreDto {

    private String name;
    private String score;
    private String date;

    public ScoreDto(String name , String score , String date){
        this.name = name;
        this.score = score;
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public String getScore() {
        return score;
    }
    public String getDate() {
        return date;
    }
}
