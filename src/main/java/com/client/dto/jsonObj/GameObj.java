package com.client.dto.jsonObj;

import lombok.Getter;

import java.util.List;

@Getter
public class GameObj {
    String id;
    String user1;
    String user2;
    boolean turnUser1;
    boolean turnUser2;
    boolean searchUser;
    List<Action> actions;

}
