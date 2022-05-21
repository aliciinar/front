package com.client.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SceneController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    public SceneController(Scene scene) {
        this.main = scene;
    }

    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        main.setRoot( screenMap.get(name) );
    }

    public Scene getScene(){
        return main;
    }
}
