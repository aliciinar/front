package com.client.dto;

import lombok.Data;

@Data
public class GameDto {
    private String name;

    public GameDto(String name) {
        this.name = name;
    }
}
