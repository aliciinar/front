package com.client.dto.jsonObj;

import lombok.Getter;

/**
 * This class is converting database informations to actions class during multiplayer game
 */
@Getter
public class Action {

    private  int id;
    private String name;
    private String type;
    int dice1,dice2;


}
